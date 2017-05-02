/**
 * HRUtils-SB
 *
 * @author Victor Kryzhanivskyi
 */
package victor.kryz.hr.sb.repositories;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
import victor.kryz.hr.sb.repositories.DepartmentsRepository.GetDepartmentsByLocationProcedure;
import victor.kryz.hr.sb.utils.Converter;
import victor.kryz.hr.sb.utils.StmtCache;
import victor.kryz.hrutils.generated.ents.HrUtilsLocationsEntryT;
import victor.kryz.hrutils.generated.ents.HrutilsLocationsT;
import victor.kryz.hrutils.generated.ents.StringListT;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;

@Repository
public class LocationsRepository 
{
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private DbPkgConfig pkgCfg;
	@Autowired
	private StmtCache<SimpleJdbcCall> simpJdbcCallsCache;
	
	final static String PROCEDURE_NAME = "GET_LOCATIONS";
	final static String PARAM_COUNTRY_ID = "p_country_id";
	final static String PARAM_LOCATIONS = "p_locations";
	final static String PARAM_NAMES_FILTER = "p_names_filter";
	
	/**
	 * 
	 * @param strCountryId
	 * @return
	 * @throws SQLException
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public List<HrUtilsLocationsEntryT> findLocationsByCountryId(String strCountryId) throws SQLException
	{
		final String cacheKey = "NkX8MhETM";
		
		SimpleJdbcCall jdbcCall = 
			simpJdbcCallsCache.getStmt(cacheKey,
			  new Callable<SimpleJdbcCall>() {
				@Override
			    public SimpleJdbcCall call()  {
					SimpleJdbcCall jdbcCall = 
							new SimpleJdbcCall(jdbcTemplate)
								.withSchemaName(pkgCfg.getSchemaName())
								.withCatalogName(pkgCfg.getPkgName())
								.withProcedureName(PROCEDURE_NAME)
								.withoutProcedureColumnMetaDataAccess()
								.declareParameters(
									new SqlParameter(PARAM_COUNTRY_ID, OracleTypes.VARCHAR),
									new SqlOutParameter(PARAM_LOCATIONS,  HrutilsLocationsT._SQL_TYPECODE, HrutilsLocationsT._SQL_NAME, 
														new SqlReturnSqlData(HrutilsLocationsT.class)));
						jdbcCall.compile();
						return jdbcCall; 
					}
				});
		
		Map<String, Object> params = Collections.singletonMap(PARAM_COUNTRY_ID, strCountryId);
		Map<String, Object> outVals = jdbcCall.execute(params);
		
		HrutilsLocationsT tbItems = (HrutilsLocationsT)outVals.get(PARAM_LOCATIONS);
		return Arrays.asList(tbItems.getArray());
	}
	
	/**
	 * 
	 * @param namesFilter
	 * @return
	 * @throws SQLException
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public List<HrUtilsLocationsEntryT> findLocationsByNames(final List<String> namesFilter) throws SQLException
	{
		final String cacheKey = "N1sYM3ETz";
		
		SimpleJdbcCall jdbcCall = 
				simpJdbcCallsCache.getStmt(cacheKey,
				  new Callable<SimpleJdbcCall>() {
					@Override
				    public SimpleJdbcCall call()  {
						SimpleJdbcCall jdbcCall = 
								new SimpleJdbcCall(jdbcTemplate)
									.withSchemaName(pkgCfg.getSchemaName())
									.withCatalogName(pkgCfg.getPkgName())
									.withProcedureName(PROCEDURE_NAME)
									.withoutProcedureColumnMetaDataAccess()
									.declareParameters(
										new SqlParameter(PARAM_NAMES_FILTER, StringListT._SQL_TYPECODE, StringListT. _SQL_NAME),
										new SqlOutParameter(PARAM_LOCATIONS,  HrutilsLocationsT._SQL_TYPECODE, HrutilsLocationsT._SQL_NAME, 
															new SqlReturnSqlData(HrutilsLocationsT.class)));
							jdbcCall.compile();
							return jdbcCall;
						}
					});
		
		Map<String, Object> params = Collections.singletonMap(PARAM_NAMES_FILTER, 
															  Converter.listToObjList(namesFilter));
		Map<String, Object> outVals = jdbcCall.execute(params);
		
		HrutilsLocationsT tbItems = (HrutilsLocationsT)outVals.get(PARAM_LOCATIONS);
		return Arrays.asList(tbItems.getArray());
	}
}
