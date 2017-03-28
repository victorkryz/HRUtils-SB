package victor.kryz.hr.sb.repositories;
import java.math.BigDecimal;
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

import oracle.jdbc.internal.OracleTypes;
import victor.kryz.hr.sb.DbPkgConfig;
import victor.kryz.hrutils.ents.LocationsEntryT;
import victor.kryz.hrutils.ents.LocationsT;

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
					.declareParameters(
						new SqlParameter(strParam_country_id, OracleTypes.VARCHAR),
						new SqlOutParameter(strParam_locations,  LocationsT._SQL_TYPECODE, LocationsT._SQL_NAME, 
											new SqlReturnSqlData(LocationsT.class)));
		
//		Map<String, Object> params = Collections.singletonMap(strParam_country_id, strCountryId);
		Map<String, Object> params = new HashMap<String, Object>();
		
		
		LocationsT locs = new LocationsT();
		
		params.put(strParam_country_id, strCountryId);
		params.put(strParam_locations, strCountryId);
		
		Map<String, Object> outVals = jdbcCall.execute(params);
		
		LocationsT tbItems = (LocationsT)outVals.get(strParam_locations);
		return tbItems.getArray();
	}
}
