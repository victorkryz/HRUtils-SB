/**
 * HRUtils-SB
 *
 * @author Victor Kryzhanivskyi
 */
package victor.kryz.hr.sb.repositories;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.support.oracle.SqlReturnSqlData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import victor.kryz.hr.sb.DbPkgConfig;
import victor.kryz.hr.sb.utils.Converter;
import victor.kryz.hr.sb.utils.StmtCache;
import victor.kryz.hrutils.generated.ents.HrUtilsRegionsEntryT;
import victor.kryz.hrutils.generated.ents.HrutilsRegionsT;
import victor.kryz.hrutils.generated.ents.StringListT;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;

@Repository
public class RegionsRepository 
{
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private DbPkgConfig pkgCfg;
	@Autowired
	private StmtCache<SimpleJdbcCall> simpJdbcCallsCache;
	
	/**
	 * 
	 * @param namesFilter
	 * @return 
	 * @throws SQLException
	 */
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public HrUtilsRegionsEntryT[] findRegions(Optional<List<String>> namesFilter) throws SQLException
	{
		final String param_region = "p_regions";
		final String param_names_filter = "p_names_filter";
		final String param_procedure_name = "GET_REGIONS";

		final String cacheKey = "4JK3Q3VTz";
		
		// obtain SimpleJdbcCall object from cache 
		// (or create and prepare it if one doesn't exist in cache) 
		SimpleJdbcCall jdbcCall = 
			simpJdbcCallsCache.getStmt(cacheKey,
				new Callable<SimpleJdbcCall>() {
					 @Override
					    public SimpleJdbcCall call()  {
						 SimpleJdbcCall jdbcCall = 
							new SimpleJdbcCall(jdbcTemplate)
								.withSchemaName(pkgCfg.getSchemaName())
								.withCatalogName(pkgCfg.getPkgName())
								.withProcedureName(param_procedure_name)
								.declareParameters(
									new SqlOutParameter(param_region,  
														HrutilsRegionsT._SQL_TYPECODE, HrutilsRegionsT._SQL_NAME, 
														new SqlReturnSqlData(HrutilsRegionsT.class)),
									new SqlParameter(param_names_filter, 
													 StringListT._SQL_TYPECODE, StringListT. _SQL_NAME));
						 
						 jdbcCall.compile();
						 return jdbcCall;
					    }
					  });
		
		// prepare parameters:		
		final StringListT filter = namesFilter.isPresent() ?
								   Converter.listToObjList(namesFilter.get()) : null;
		final Map<String, Object> params = Collections.singletonMap(param_names_filter, filter);
		
		// execute procedure:
		Map<String, Object> outVals = jdbcCall.execute(params);
		
		//obtain result:
		HrutilsRegionsT tbRegs = (HrutilsRegionsT)outVals.get(param_region);
		return tbRegs.getArray();
	}
}
