package victor.kryz.hr.sb.repositories;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.support.oracle.SqlReturnSqlData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import bitronix.tm.resource.jdbc.PoolingDataSource;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.internal.OracleTypes;
import oracle.sql.ORAData;
import victor.kryz.hr.sb.DbPkgConfig;
import victor.kryz.hr.sb.ents.EmployeeBriefEntryT;
import victor.kryz.hr.sb.utils.Converter;
import victor.kryz.hr.sb.utils.NativeJdbcConnection;
import victor.kryz.hrutils.ents.HrUtilsDepartmentsEntryT;
import victor.kryz.hrutils.ents.HrUtilsLocationsEntryT;
import victor.kryz.hrutils.ents.HrUtilsRegionsEntryT;
import victor.kryz.hrutils.ents.HrutilsDepartmentsMapT;
import victor.kryz.hrutils.ents.HrutilsLocationsT;
import victor.kryz.hrutils.ents.HrutilsRegionsT;
import victor.kryz.hrutils.ents.StringListT;

@Repository
public class Recycle {
	
	private static final Logger LOG = LoggerFactory.getLogger(Recycle.class);
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private DbPkgConfig pkgCfg;

	
	
	void test1()
	{
		String strType = "HR_UTILS.REGIONS_T";
		System.out.println(strType);
		
		
        Hashtable tmap = new Hashtable();
        try {
//	        tmap.put(RegionsT._SQL_NAME,  RegionsT.class);
			tmap.put(strType,  HrutilsRegionsT.class);
			jdbcTemplate.getDataSource().getConnection().setTypeMap(tmap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SimpleJdbcCall jdbcCall = 
			new SimpleJdbcCall(jdbcTemplate)
				.withSchemaName("HR")
				.withCatalogName("HR_UTILS")
				.withProcedureName("GET_REGIONS")
				.declareParameters(
						new SqlOutParameter("p_regions",  HrutilsRegionsT._SQL_TYPECODE, strType, 
											 new SqlReturnSqlData(HrutilsRegionsT.class)),
						new SqlParameter("p_names_filter", StringListT._SQL_TYPECODE, StringListT. _SQL_NAME));
		
		jdbcCall.setSchemaName("");
		
		Map params = Collections.singletonMap("p_names_filter", null);
		Map out = jdbcCall.execute(params);
		
		HrutilsRegionsT tbRegs = (HrutilsRegionsT)out.get("p_regions");
		try {
			HrUtilsRegionsEntryT[] regs = tbRegs.getArray();
			
			for ( HrUtilsRegionsEntryT entry : regs)
			{
				System.out.println("-------------------");
				System.out.println(entry.getRegionId());
				System.out.println(entry.getRegionName());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	void test2()
	{
		SimpleJdbcCall jdbcCall = 
					new SimpleJdbcCall(jdbcTemplate)
						.withProcedureName("hr_utils.get_regions")
						.declareParameters(
								new SqlParameter("p_regions", HrutilsRegionsT._SQL_TYPECODE, HrutilsRegionsT. _SQL_NAME));
		
	}
	
	
	void test3()
	{
		SimpleJdbcCall jdbcCall = 
			new SimpleJdbcCall(jdbcTemplate)
				.withCatalogName("HR_UTILS")
				.withProcedureName("TEST_01")
				.declareParameters(
						new SqlOutParameter("p_regions", OracleTypes.INTEGER)); 
		
		Map out = jdbcCall.execute();
		
		System.out.println(out.toString());
	}
	
	
	void test4()
	{
		
		String strCmd = "begin hr_utils.get_regions(?); end;";
		
//		String strType = "\"\"HR_UTILS.REGIONS_T\"\"";
//		String strType = "{\"HR_UTILS.REGIONS_T\"}";
		String strType = "HR_UTILS.REGIONS_T";
		System.out.println(strType);
		
		OracleConnection conn = null;
		try {
			conn = (OracleConnection) jdbcTemplate.getDataSource().getConnection();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        Hashtable tmap = new Hashtable();
        OracleCallableStatement stmt = null;
        
        try {
        	
			tmap.put(strType,  HrutilsRegionsT.class);
			conn .setTypeMap(tmap);
        	stmt = (OracleCallableStatement)conn.prepareCall(strCmd);
			stmt.registerOutParameter(1, HrutilsRegionsT._SQL_TYPECODE, strType);
			stmt.executeUpdate();
			ORAData resData = (oracle.sql.ORAData)
				      (stmt.getORAData(1, HrutilsRegionsT.getORADataFactory()));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
//
//        
//		
//		SimpleJdbcCall jdbcCall = 
//			new SimpleJdbcCall(jdbcTemplate)
//				.withSchemaName("")
//				.withCatalogName("HR_UTILS")
//				.withProcedureName("GET_REGIONS")
//				.declareParameters(
//						new SqlOutParameter("p_regions", RegionsT._SQL_TYPECODE, strType // RegionsT. _SQL_NAME,
//											 ),
//						new SqlParameter("p_names_filter", StringListT._SQL_TYPECODE, StringListT. _SQL_NAME));
//		
//		jdbcCall.setSchemaName("");
//		
//		Map params = Collections.singletonMap("p_names_filter", null);
//		Map out = jdbcCall.execute(params);
//		
//		System.out.println(out.toString());
	}
	
	public void getDepartmentsStat_bak(final Optional<BigDecimal> depId) throws SQLException
	{
		
		String strCmd = "begin ? := hr_utils.get_department_stat(?); end;";
		
    	OracleConnection conn =  NativeJdbcConnection.
    									obtainOracleConnection(jdbcTemplate.getDataSource()); 
    	
        OracleCallableStatement stmt = (OracleCallableStatement)conn.prepareCall(strCmd);
        
        stmt.setNull(2, OracleTypes.NUMBER);
        stmt.registerOutParameter(1, OracleTypes.CURSOR);
        		        				
		stmt.executeUpdate();
		
		ResultSet cursor = ((OracleCallableStatement)stmt).getCursor(1);
		
		try
		{
			ResultSetMetaData rsMeta =  cursor.getMetaData();
			
			int citems = rsMeta.getColumnCount();
			for ( int i = 1; i <= citems; i++ )
			{
				String c = rsMeta.getColumnName(i);
				LOG.info("Column " + i + " name  : " + c);
			}

			
		}
		finally {
			
			cursor.close();
			
		}
	}
	
	
	public HrUtilsLocationsEntryT[] test13()
	{
			String strCmd = "begin hr_utils.get_locations(?,?); end;";
			
			bitronix.tm.resource.jdbc.PoolingDataSource ds = (PoolingDataSource) jdbcTemplate.getDataSource();
			
	        try {
	        	
	        	OracleConnection conn = null; 
	        	
	        	Connection c = ds.getConnection();
	        	DatabaseMetaData dmd = c.getMetaData();
	        	conn = (OracleConnection)dmd.getConnection();
	        	
	        	
	        	
//	        	if ( c.isWrapperFor(OracleConnection.class) )
//	        		 conn = (OracleConnection)c.unwrap(OracleConnection.class)._getPC();
	        	
//	        	conn = (OracleConnection) jdbcTemplate.getDataSource().getConnection();
			
		        Hashtable tmap = new Hashtable();
		        OracleCallableStatement stmt = null;
	        	
				tmap.put(HrutilsLocationsT._SQL_NAME,  HrutilsLocationsT.class);
				conn .setTypeMap(tmap);
				
	        	stmt = (OracleCallableStatement)conn.prepareCall(strCmd);
	        	stmt.setString(1, "UK");
				stmt.registerOutParameter(2, HrutilsLocationsT._SQL_TYPECODE, HrutilsLocationsT._SQL_NAME);
				
				stmt.executeUpdate();
				ORAData resData = (oracle.sql.ORAData)
					      (stmt.getORAData(2, HrutilsLocationsT.getORADataFactory()));
				
				HrutilsLocationsT tbItems = (HrutilsLocationsT)resData;
				
				return tbItems.getArray(); 
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
	
	
	public void test11()
	{
			String strCmd = "begin hr_utils.get_departments(?,?); end;";
			
			bitronix.tm.resource.jdbc.PoolingDataSource ds = (PoolingDataSource) jdbcTemplate.getDataSource();
			
	        try {
	        	
	        	OracleConnection conn = null; 
	        	
	        	Connection c = ds.getConnection();
	        	DatabaseMetaData dmd = c.getMetaData();
	        	conn = (OracleConnection)dmd.getConnection();
	        	
	        	List<String> namesFilterList = Arrays.asList(new String[] {"Administration", "Finance", "IT", 
						   "Public Relations", "Recruiting"});
	        	StringListT strList = Converter.listToObjList(namesFilterList);

	        	
	        	
	        	
//	        	if ( c.isWrapperFor(OracleConnection.class) )
//	        		 conn = (OracleConnection)c.unwrap(OracleConnection.class)._getPC();
	        	
//	        	conn = (OracleConnection) jdbcTemplate.getDataSource().getConnection();
			
		        Hashtable tmap = new Hashtable();
		        OracleCallableStatement stmt = null;
	        	
		        tmap.put(StringListT._SQL_NAME,  StringListT.class);
				tmap.put(HrUtilsDepartmentsEntryT._SQL_NAME,  HrUtilsDepartmentsEntryT.class);
				tmap.put(HrutilsDepartmentsMapT._SQL_NAME,  HrutilsDepartmentsMapT.class);
				
				conn .setTypeMap(tmap);
				
	        	stmt = (OracleCallableStatement)conn.prepareCall(strCmd);
	        	stmt.setObject(1, strList);
	        	
//	        	stmt.registerIndexTableOutParameter(2,50, HrUtilsDepartmentsEntryT._SQL_TYPECODE, );
//	        	stmt.registerOutParameter(2, HrutilsDepartmentsMapT._SQL_TYPECODE, HrutilsDepartmentsMapT._SQL_NAME);
				stmt.registerOutParameter(2, HrutilsDepartmentsMapT._SQL_TYPECODE, HrutilsDepartmentsMapT._SQL_NAME);
//				stmt.registerOutParameter(2, HrutilsCountriesT._SQL_TYPECODE, HrutilsCountriesT._SQL_NAME);
				
				stmt.executeUpdate();
				ORAData resData = (oracle.sql.ORAData)
					      (stmt.getORAData(2, HrutilsDepartmentsMapT.getORADataFactory()));
				
				HrutilsDepartmentsMapT tbItems = (HrutilsDepartmentsMapT)resData;
				
//				return tbItems.getArray(); 
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				String msg = e.getLocalizedMessage();
//				System.out.println(msg);
				e.printStackTrace();
			}
//			return null;
	}
	
	public List<EmployeeBriefEntryT> getEmployeesWithJobHistory(Optional<BigDecimal> depId) throws SQLException
	{
			String strCmd = "begin ? := hr_utils.get_employees_with_job_history(?); end;";
			
        	OracleConnection conn = NativeJdbcConnection.obtainOracleConnection(jdbcTemplate.getDataSource());  
        	
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
