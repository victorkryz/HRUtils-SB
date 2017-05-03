/**
 * Project "HRUtils-SB"
 */
package victor.kryz.hr.sb.repositories;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import victor.kryz.hr.sb.ents.EmployeeBriefEntryT;
import victor.kryz.hr.sb.utils.Converter;
import victor.kryz.hrutils.generated.ents.EmployeeDescrT;
import victor.kryz.hrutils.generated.ents.EmployeeSetT;
import victor.kryz.hrutils.generated.ents.HrUtilsJobHistoryEntryT;
import victor.kryz.hrutils.generated.ents.HrutilsJobHistoryT;
import victor.kryz.hrutils.generated.ents.NumberListT;

/**
 * Repository exposes interface to "Employee" entity
 *    
 * @author Victor Kryzhanivskyi
 */
@Repository
public class EmployeesRepository {
	
	private static final Logger LOG = LoggerFactory.getLogger(EmployeesRepository.class);
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	/**
	 * Calls procedure HR_UTILS.GET_EMPLOYEES_BY_FULL_NAME()
	 * 
	 * @param names - list of employee full names
	 *                forms by pattern: [first name][space][last name] 
	 * @return list of employee identifiers
	 * @throws SQLException
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public List<BigDecimal> findEmployeesByFullName(final List<String> names) throws SQLException
	{
			final String strStmt = "call hr_utils.get_employees_by_full_name(?,?)";
        	
			CallableStatement stmt = prepareStmt(strStmt);
			
        	try
        	{
		        final int namesIndex = 1;
		        final int idsIndex = 2;
		        
		        obtainOraStmt(stmt).setORAData(namesIndex, Converter.listToObjList(names)); 
		        stmt.registerOutParameter(idsIndex, NumberListT._SQL_TYPECODE, NumberListT._SQL_NAME);
		        
				stmt.executeUpdate();
				
				final NumberListT ids = (NumberListT)
					      (obtainOraStmt(stmt).getORAData(idsIndex, NumberListT.getORADataFactory()));
				
				return Arrays.asList(ids.getArray());
        	}
        	finally {
				stmt.close();
			}
	}
	
	/**
	 * Calls function HR_UTILS.GET_EMPLOYEES_WITH_JOB_HISTORY()
	 * 
	 * @param depId - department identifier
	 * @return list of EmployeeBriefEntryT objects
	 * @throws SQLException
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public List<EmployeeBriefEntryT> findEmployeesWithJobHistory(Optional<BigDecimal> depId) throws SQLException
	{
			final String strStmt = "begin ? := hr_utils.get_employees_with_job_history(?); end;";
			
			CallableStatement stmt = prepareStmt(strStmt);
			ResultSet cursor = null;
        	
        	try
        	{
		        final int emplCursorIndex = 1;
		        final int depIdIndex = 2;
		        
		        stmt.registerOutParameter(emplCursorIndex, OracleTypes.CURSOR);
		        if ( depId.isPresent() )
		        	stmt.setBigDecimal(depIdIndex, depId.get());
		        else
		        	stmt.setNull(depIdIndex, OracleTypes.NUMBER);
		        		        				
				stmt.executeUpdate();
				
				cursor = obtainOraStmt(stmt).getCursor(emplCursorIndex);
				
				//& describeCursor(cursor);
				
				ArrayList<EmployeeBriefEntryT> list = new ArrayList<EmployeeBriefEntryT>();
				
					while (cursor.next())
					{
						list.add(new EmployeeBriefEntryT(
										 cursor.getBigDecimal("EMPLOYEE_ID"),
										 cursor.getString("FIRST_NAME"),
										 cursor.getString("LAST_NAME"),
										 cursor.getBigDecimal("DEPARTMENT_ID")));
					}
				
				return list;
        	}
        	finally 
        	{
        		if ( null != cursor)
        			cursor.close();
				stmt.close();
			}
	}
	
	
	/**
	 * Calls procedure HR_UTILS.GET_JOB_HISTORY()
	 * 
	 * @param emplId - department identifier
	 * @return list of HrUtilsJobHistoryEntryT objects
	 * @throws SQLException
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public List<HrUtilsJobHistoryEntryT> getJobHistory(BigDecimal emplId) throws SQLException
	{
			final String strStmt = "begin hr_utils.get_job_history(?,?); end;";
			
			CallableStatement stmt = prepareStmt(strStmt);
			
	        final int historyIndex = 2;
	        final int emplIdIndex = 1;
	        
	        try
	        {
		        stmt.setBigDecimal(emplIdIndex, emplId);
		        stmt.registerOutParameter(historyIndex, 
		        						  HrutilsJobHistoryT._SQL_TYPECODE, HrutilsJobHistoryT._SQL_NAME); 
				stmt.executeUpdate();
				
				HrutilsJobHistoryT tbItems = (HrutilsJobHistoryT)obtainOraStmt(stmt).
													getORAData(historyIndex, HrutilsJobHistoryT.getORADataFactory());
				return Arrays.asList(tbItems.getArray());
	        }
	        finally {
				stmt.close();
			}
	}
	/**
	 * Calls procedure HR_UTILS.ADD_EMPLOYEES()
	 * 
	 * @param (in/out) employees - list of EmployeeDescrT objects
	 * @return - fills identifier of each new employee 
	 * 			 in appropriate EmployeeDescrT object 
	 * @throws SQLException
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public void addEmployees(final List<EmployeeDescrT> employees) throws SQLException
	{
			final String strStmt = "call hr_utils.add_employees(?)";
			
			CallableStatement stmt = prepareStmt(strStmt);
        	
			try
			{
				stmt.registerOutParameter(1, EmployeeSetT._SQL_TYPECODE, EmployeeSetT._SQL_NAME);
				obtainOraStmt(stmt).setORAData(1, new EmployeeSetT(employees.toArray(new EmployeeDescrT[employees.size()])));
				
				stmt.executeUpdate();
				
				final EmployeeSetT eset = (EmployeeSetT)obtainOraStmt(stmt).
										  				getORAData(1, EmployeeSetT.getORADataFactory());
				
				final EmployeeDescrT[] rg = eset.getArray();
				for ( int i = 0; i < rg.length; i++ ) {
					 employees.get(i).setEmplId(rg[i].getEmplId());
				}
				
				List<BigDecimal> addedIds = new ArrayList<BigDecimal>();  
				for (EmployeeDescrT item :  employees) {
					addedIds.add(item.getEmplId());
				}
			}
			finally {
				stmt.close();
			}
	}
	
	
	/**
	 * Calls procedure HR_UTILS.REMOVE_EMPLOYEES()
	 * 
	 * @param emplIds - list of employee identifiers to remove
	 * @return number of removed employees
	 * @throws SQLException
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public int removeEmployees(List<BigDecimal> emplIds) throws SQLException
	{
			final String strStmt = "begin ? := hr_utils.remove_employees(?); end;";
			
			CallableStatement stmt = prepareStmt(strStmt);
        	
			final int idsIndex = 2;
	        final int countIndex = 1;
			
	        try
	        {
				obtainOraStmt(stmt).setORAData(idsIndex, new NumberListT(emplIds.toArray(new BigDecimal[emplIds.size()]))); 
				stmt.registerOutParameter(countIndex, OracleTypes.INTEGER); 
				
				stmt.executeUpdate();
				
				int count = stmt.getInt(countIndex);
				
				return count;
	        }
	        finally {
				stmt.close();
			}
	}
	
	private CallableStatement prepareStmt(String strStmt) throws SQLException {
		Connection conn = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
		return prepareStmt(conn, strStmt); 
	}
	
	private CallableStatement prepareStmt(Connection conn, String strStmt) throws SQLException {
		return conn.prepareCall(strStmt);
	}
	
	
	private OracleCallableStatement obtainOraStmt(CallableStatement stmt) throws SQLException {
		return (OracleCallableStatement)stmt.unwrap(OracleCallableStatement.class);
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
