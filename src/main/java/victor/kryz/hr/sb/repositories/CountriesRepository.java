package victor.kryz.hr.sb.repositories;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

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
import victor.kryz.hrutils.ents.CountriesEntryT;
import victor.kryz.hrutils.ents.CountriesT;
import victor.kryz.hrutils.ents.RegionsEntryT;
import victor.kryz.hrutils.ents.RegionsT;
import victor.kryz.hrutils.ents.StringListT;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;

@Repository
public class CountriesRepository 
{
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private DbPkgConfig pkgCfg;
	
	CountriesRepository() {
	}
	
	public CountriesEntryT[] getCountries(BigDecimal regId) throws SQLException
	{
		final String strParam_region_id = "p_region_id";
		final String strParam_countries = "p_countries";
		
		SimpleJdbcCall jdbcCall = 
				new SimpleJdbcCall(jdbcTemplate)
					.withSchemaName(pkgCfg.getSchemaName())
					.withCatalogName(pkgCfg.getPkgName())
					.withProcedureName("GET_COUNTRIES")
					.declareParameters(
						new SqlParameter(strParam_region_id, OracleTypes.INTEGER),
						new SqlOutParameter(strParam_countries,  CountriesT._SQL_TYPECODE, CountriesT._SQL_NAME, 
											new SqlReturnSqlData(CountriesT.class)));
		
		Map<String, Object> params = Collections.singletonMap(strParam_region_id, regId);
		Map<String, Object> outVals = jdbcCall.execute(params);
		
		CountriesT tbItems = (CountriesT)outVals.get(strParam_countries);
		return tbItems.getArray();
	}
}
