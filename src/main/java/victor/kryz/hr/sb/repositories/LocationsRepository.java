package victor.kryz.hr.sb.repositories;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.support.oracle.SqlReturnSqlData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import bitronix.tm.resource.jdbc.PoolingDataSource;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.internal.OracleTypes;
import oracle.sql.ORAData;
import victor.kryz.hr.sb.DbPkgConfig;
import victor.kryz.hr.sb.utils.Converter;
import victor.kryz.hrutils.ents.LocationsEntryT;
import victor.kryz.hrutils.ents.LocationsT;
import victor.kryz.hrutils.ents.StringListT;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;

@Repository
public class LocationsRepository 
{
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private DbPkgConfig pkgCfg;
	
	LocationsRepository() {
	}
	
	public LocationsEntryT[] getLocations(String strCountryId) throws SQLException
	{
		final String strParam_country_id = "p_country_id";
		final String strParam_locations = "p_locations";
		
		SimpleJdbcCall jdbcCall = 
				new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(pkgCfg.getSchemaName())
					.withCatalogName(pkgCfg.getPkgName())
					.withProcedureName("GET_LOCATIONS")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(
						new SqlParameter(strParam_country_id, OracleTypes.VARCHAR),
						new SqlOutParameter(strParam_locations,  LocationsT._SQL_TYPECODE, LocationsT._SQL_NAME, 
											new SqlReturnSqlData(LocationsT.class)));
		
		Map<String, Object> params = Collections.singletonMap(strParam_country_id, strCountryId);
//		Map<String, Object> params = new HashMap<String, Object>();
		
		
		LocationsT locs = new LocationsT();
		
//		params.put(strParam_country_id, strCountryId);
//		params.put(strParam_locations, strCountryId);
		
		Map<String, Object> outVals = jdbcCall.execute(params);
		
		LocationsT tbItems = (LocationsT)outVals.get(strParam_locations);
		return tbItems.getArray();
	}
	
	
	
	public LocationsEntryT[] getLocations(final List<String> namesFilter) throws SQLException
	{
		final String strParam_names_filter = "p_names_filter";
		final String strParam_locations = "p_locations";
		
		SimpleJdbcCall jdbcCall = 
				new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(pkgCfg.getSchemaName())
					.withCatalogName(pkgCfg.getPkgName())
					.withProcedureName("GET_LOCATIONS")
					.withoutProcedureColumnMetaDataAccess()
					.declareParameters(
						new SqlParameter(strParam_names_filter, StringListT._SQL_TYPECODE, StringListT. _SQL_NAME),
						new SqlOutParameter(strParam_locations,  LocationsT._SQL_TYPECODE, LocationsT._SQL_NAME, 
											new SqlReturnSqlData(LocationsT.class)));
		
		Map<String, Object> params = Collections.singletonMap(strParam_names_filter, 
															  Converter.listToObjList(namesFilter));
		Map<String, Object> outVals = jdbcCall.execute(params);
		
		LocationsT tbItems = (LocationsT)outVals.get(strParam_locations);
		return tbItems.getArray();
	}
	
	
	public LocationsEntryT[] test1()
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
	        	
				tmap.put(LocationsT._SQL_NAME,  LocationsT.class);
				conn .setTypeMap(tmap);
				
	        	stmt = (OracleCallableStatement)conn.prepareCall(strCmd);
	        	stmt.setString(1, "UK");
				stmt.registerOutParameter(2, LocationsT._SQL_TYPECODE, LocationsT._SQL_NAME);
				
				stmt.executeUpdate();
				ORAData resData = (oracle.sql.ORAData)
					      (stmt.getORAData(2, LocationsT.getORADataFactory()));
				
				LocationsT tbItems = (LocationsT)resData;
				
				return tbItems.getArray(); 
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
	}
}
