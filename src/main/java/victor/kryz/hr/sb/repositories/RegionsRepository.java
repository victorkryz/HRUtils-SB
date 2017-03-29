package victor.kryz.hr.sb.repositories;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.support.oracle.SqlReturnSqlData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.internal.OracleTypes;
import oracle.sql.ORAData;
import victor.kryz.hr.sb.DbPkgConfig;
import victor.kryz.hr.sb.utils.Converter;
import victor.kryz.hrutils.ents.RegionsEntryT;
import victor.kryz.hrutils.ents.RegionsT;
import victor.kryz.hrutils.ents.StringListT;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;

@Repository
public class RegionsRepository 
{
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private DbPkgConfig pkgCfg;
	
	RegionsRepository() {
	}
	
	public RegionsEntryT[] getRegions(Optional<List<String>> namesFilter) throws SQLException
	{
		final String strParam_region = "p_regions";
		final String strParam_names_filter = "p_names_filter";
		
		SimpleJdbcCall jdbcCall = 
				new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(pkgCfg.getSchemaName())
					.withCatalogName(pkgCfg.getPkgName())
					.withProcedureName("GET_REGIONS")
					.declareParameters(
						new SqlOutParameter(strParam_region,  RegionsT._SQL_TYPECODE, RegionsT._SQL_NAME, 
											new SqlReturnSqlData(RegionsT.class)),
						new SqlParameter(strParam_names_filter, StringListT._SQL_TYPECODE, StringListT. _SQL_NAME));
		
		final StringListT filter = namesFilter.isPresent() ?
								   Converter.listToObjList(namesFilter.get()) : null;
		final Map<String, Object> params = Collections.singletonMap(strParam_names_filter, filter);
		
		Map<String, Object> outVals = jdbcCall.execute(params);
		
		RegionsT tbRegs = (RegionsT)outVals.get(strParam_region);
		return tbRegs.getArray();
	}
	
	void test1()
	{
		String strType = "HR_UTILS.REGIONS_T";
		System.out.println(strType);
		
		
        Hashtable tmap = new Hashtable();
        try {
//	        tmap.put(RegionsT._SQL_NAME,  RegionsT.class);
			tmap.put(strType,  RegionsT.class);
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
						new SqlOutParameter("p_regions",  RegionsT._SQL_TYPECODE, strType, 
											 new SqlReturnSqlData(RegionsT.class)),
						new SqlParameter("p_names_filter", StringListT._SQL_TYPECODE, StringListT. _SQL_NAME));
		
		jdbcCall.setSchemaName("");
		
		Map params = Collections.singletonMap("p_names_filter", null);
		Map out = jdbcCall.execute(params);
		
		RegionsT tbRegs = (RegionsT)out.get("p_regions");
		try {
			RegionsEntryT[] regs = tbRegs.getArray();
			
			for ( RegionsEntryT entry : regs)
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
								new SqlParameter("p_regions", RegionsT._SQL_TYPECODE, RegionsT. _SQL_NAME));
		
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
        	
			tmap.put(strType,  RegionsT.class);
			conn .setTypeMap(tmap);
        	stmt = (OracleCallableStatement)conn.prepareCall(strCmd);
			stmt.registerOutParameter(1, RegionsT._SQL_TYPECODE, strType);
			stmt.executeUpdate();
			ORAData resData = (oracle.sql.ORAData)
				      (stmt.getORAData(1, RegionsT.getORADataFactory()));
			
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

}
