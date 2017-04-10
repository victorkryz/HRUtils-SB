package victor.kryz.hr.sb.repositories;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
//import org.springframework.jdbc.support.nativejdbc.NativeJdbcExtractor;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleTypes;
import victor.kryz.hr.sb.ents.EmployeeBriefEntryT;
import victor.kryz.hr.sb.utils.NativeJdbcConnection;
import victor.kryz.hr.sb.utils.StmtCache;

@Repository
public class EmployeesRepository {
	
	private static final Logger LOG = LoggerFactory.getLogger(EmployeesRepository.class);
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private StmtCache<OracleCallableStatement> nativeJdbcStmtCache;

	
	/**
	 * 
	 * @param depId
	 * @return
	 * @throws SQLException
	 */
	public List<EmployeeBriefEntryT> getEmployeesWithJobHistory(Optional<BigDecimal> depId) throws SQLException
	{
			final String strStmt = "begin ? := hr_utils.get_employees_with_job_history(?); end;";
        	
			OracleCallableStatement stmt =
					nativeJdbcStmtCache.getStmt("E1Z7B1SaM", 
        				new Callable<OracleCallableStatement>() {
							@Override
							public OracleCallableStatement call() throws Exception {
								OracleConnection conn = NativeJdbcConnection.
											obtainOracleConnection(jdbcTemplate.getDataSource());
								return (OracleCallableStatement)conn.prepareCall(strStmt);
							}
        				});
        	
        	
	        final int emplCursorIndex = 1;
	        final int depIdIndex = 2;
	        
	        stmt.registerOutParameter(emplCursorIndex, OracleTypes.CURSOR);
	        if ( depId.isPresent() )
	        	stmt.setBigDecimal(depIdIndex, depId.get());
	        else
	        	stmt.setNull(depIdIndex, OracleTypes.NUMBER);
	        		        				
			stmt.executeUpdate();
			
			ResultSet cursor = ((OracleCallableStatement)stmt).getCursor(1);
			
			//& describeCursor(cursor);
			
			ArrayList<EmployeeBriefEntryT> list = new ArrayList<EmployeeBriefEntryT>();
			
			try
			{
				while (cursor.next())
				{
					list.add(new EmployeeBriefEntryT(
									 cursor.getBigDecimal("EMPLOYEE_ID"),
									 cursor.getString("FIRST_NAME"),
									 cursor.getString("LAST_NAME"),
									 cursor.getBigDecimal("DEPARTMENT_ID")));
				}
			}
			finally {
				cursor.close();
			}
			
			return list;
	}
	
	
	@SuppressWarnings("unused")
	private void describeCursor(ResultSet cursor) throws SQLException
	{
		ResultSetMetaData rsMeta =  cursor.getMetaData();
		
		int citems = rsMeta.getColumnCount();
		for ( int i = 1; i <= citems; i++ )
		{
			String c = rsMeta.getColumnName(i);
			LOG.info("Column " + i + " name  : " + c);
		}
	}	
}
