/**
 * Project "HRUtils-SB"
 */
package victor.kryz.hr.sb.repositories;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.support.oracle.SqlReturnSqlData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import oracle.jdbc.internal.OracleTypes;
import victor.kryz.hr.sb.DbPkgConfig;
import victor.kryz.hr.sb.utils.StmtCache;
import victor.kryz.hrutils.generated.ents.HrUtilsCountriesEntryT;
import victor.kryz.hrutils.generated.ents.HrutilsCountriesT;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;

/**
 * Repository exposes interface to "Country" entity
 *    
 * @author Victor Kryzhanivskyi
 */
@Repository
public class CountriesRepository 
{
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private DbPkgConfig pkgCfg;
	@Autowired
	private StmtCache<SimpleJdbcCall> simpJdbcCallsCache;
	
	/**
	 * Calls package procedure HR_UTILS.GET_COUNTRIES()
	 *  
	 * @param regId - region identifier
	 * @return array of HrUtilsCountriesEntryT
	 * @throws SQLException
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public HrUtilsCountriesEntryT[] findCountriesByRegionId(BigDecimal regId) throws SQLException
	{
		final String param_region_id = "p_region_id";
		final String param_countries = "p_countries";
		final String procedure_name = "GET_COUNTRIES";

		final String cacheKey = "Nk-LQh4pG";
		
		SimpleJdbcCall jdbcCall = 
			simpJdbcCallsCache.getStmt(cacheKey,
			  new Callable<SimpleJdbcCall>() {
				@Override
			    public SimpleJdbcCall call()  {
					SimpleJdbcCall jdbcCall = 
						new SimpleJdbcCall(jdbcTemplate)
							.withSchemaName(pkgCfg.getSchemaName())
							.withCatalogName(pkgCfg.getPkgName())
							.withProcedureName(procedure_name)
							.declareParameters(
								new SqlParameter(param_region_id, OracleTypes.INTEGER),
								new SqlOutParameter(param_countries,  HrutilsCountriesT._SQL_TYPECODE, HrutilsCountriesT._SQL_NAME, 
													new SqlReturnSqlData(HrutilsCountriesT.class)));
								jdbcCall.compile();
								return jdbcCall; 
							}
					});
		
		Map<String, Object> params = Collections.singletonMap(param_region_id, regId);
		Map<String, Object> outVals = jdbcCall.execute(params);
		
		HrutilsCountriesT tbItems = (HrutilsCountriesT)outVals.get(param_countries);
		return tbItems.getArray();
	}
}
