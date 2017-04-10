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

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.support.nativejdbc.NativeJdbcExtractor;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleTypes;
import victor.kryz.hr.sb.ents.EmployeeBriefEntryT;

@Repository
public class EmployeesRepository {
	
	private static final Logger LOG = LoggerFactory.getLogger(EmployeesRepository.class);
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	EmployeesRepository() {
	}
	
	
	private static OracleConnection getNativeConnection(DataSource ds) throws SQLException
	{
		Connection conn = ds.getConnection();
    	DatabaseMetaData meta = conn.getMetaData();
    	return (OracleConnection)meta.getConnection();
	}
	
//	private OracleConnection getNativeJdbcConnection() throws SQLException
//	{
//		NativeJdbcExtractor nje = jdbcTemplate.getNativeJdbcExtractor();
//		Connection conn = ds.getConnection();
//    	DatabaseMetaData meta = conn.getMetaData();
//    	return (OracleConnection)meta.getConnection();
//	}
	
	public List<EmployeeBriefEntryT> getEmployeesWithJobHistory(Optional<BigDecimal> depId) throws SQLException
	{
			String strCmd = "begin ? := hr_utils.get_employees_with_job_history(?); end;";
			
        	OracleConnection conn =  getNativeConnection(jdbcTemplate.getDataSource()); 
        	
	        OracleCallableStatement stmt = (OracleCallableStatement)conn.prepareCall(strCmd);
	        
	        stmt.setNull(2, OracleTypes.NUMBER);
	        stmt.registerOutParameter(1, OracleTypes.CURSOR);
	        		        				
			stmt.executeUpdate();
			
			ResultSet cursor = ((OracleCallableStatement)stmt).getCursor(1);
			
			ArrayList<EmployeeBriefEntryT> list = new ArrayList<EmployeeBriefEntryT>();
			
			ResultSetMetaData rsMeta =  cursor.getMetaData();
			
			int citems = rsMeta.getColumnCount();
			for ( int i = 1; i <= citems; i++ )
			{
				String c = rsMeta.getColumnName(i);
				LOG.info("Column " + i + " name  : " + c);
			}
			
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
				
				cursor.close();
			}
			finally {
				cursor.close();
			}
			
			return list;
	}
	
	
	void test01(){
		int i = jdbcTemplate.queryForObject("select 1 from dual", Integer.class);
		System.out.println(i);
	}
	
	void test02()
	{
		List<Map<String, Object>> selList = jdbcTemplate.queryForList("select * from EMPLOYEES_CONSOLIDATED_VIEW");
		
		for ( Map<String, Object> entry : selList )
		{
			System.out.println(entry.toString());
		}
	}
}
