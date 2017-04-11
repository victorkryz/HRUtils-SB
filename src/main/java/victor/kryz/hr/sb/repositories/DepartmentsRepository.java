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
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.support.oracle.SqlReturnSqlData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcCallOperations;
import org.springframework.stereotype.Repository;

import com.google.common.base.Joiner;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import bitronix.tm.resource.jdbc.PoolingDataSource;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleTypeMetaData;
import oracle.jdbc.internal.OracleTypes;
import oracle.sql.ORAData;
import victor.kryz.hr.sb.DbPkgConfig;
import victor.kryz.hr.sb.ents.DepartmentStatisticT;
import victor.kryz.hr.sb.utils.Converter;
import victor.kryz.hr.sb.utils.StmtCache;
import victor.kryz.hrutils.ents.DepartmentDescrT;
import victor.kryz.hrutils.ents.HrUtilsDepartmentsEntryT;
import victor.kryz.hrutils.ents.HrUtilsLocationsEntryT;
import victor.kryz.hrutils.ents.HrutilsCountriesT;
import victor.kryz.hrutils.ents.HrUtilsDepartmentsEntryT;
import victor.kryz.hrutils.ents.HrutilsDepartmentsMapT;
import victor.kryz.hrutils.ents.HrutilsDepartmentsT;
import victor.kryz.hrutils.ents.HrutilsLocationsT;
import victor.kryz.hrutils.ents.StringListT;

import org.springframework.jdbc.object.StoredProcedure;

@Repository
public class DepartmentsRepository 
{
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private DbPkgConfig pkgCfg;
	@Autowired
	private StmtCache<StoredProcedure> storedProceCache;
	@Autowired
	private StmtCache<SimpleJdbcCall> simpJdbcCallsCache;
	
	static final Hashtable<String, Class<?>> typesMap;
	
	static
	{
		typesMap = new Hashtable<String, Class<?>>();
		typesMap.put(DepartmentDescrT._SQL_NAME, DepartmentDescrT.class);
	}
	
	
	/**
	 * 
	 * @param locationId
	 * @return
	 * @throws SQLException
	 */
	public List<HrUtilsDepartmentsEntryT> findDepartmentsByLocationId(final BigDecimal locationId) throws SQLException
	{
		final String cacheKey = GetDepartmentsByLocationProcedure.class.getName();
		
		GetDepartmentsByLocationProcedure proc =
					(GetDepartmentsByLocationProcedure)
						storedProceCache.getStmt(cacheKey,
										new Callable<StoredProcedure>() {
											 @Override
											    public StoredProcedure call()  {
											      return new GetDepartmentsByLocationProcedure();
											    }
											  });
		return proc.call(locationId);
	}
	
	/**
	 * 
	 * @param depId
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public List<DepartmentStatisticT> getDepartmentsStat(final Optional<BigDecimal> depId) throws SQLException
	{
		final String param_dep_id = "p_dep_id";
		final String result_key = "resultSet";
		final String procedure_name = "GET_DEPARTMENT_STAT";

		final String cacheKey = "NJE5Vn4pG";
		
		SimpleJdbcCall jdbcCall = 
			simpJdbcCallsCache.getStmt(cacheKey,
				new Callable<SimpleJdbcCall>() {
				@Override
				public SimpleJdbcCall call()  
				{
					SimpleJdbcCall jdbcCall = 
						new SimpleJdbcCall(jdbcTemplate)
							.withSchemaName(pkgCfg.getSchemaName())
							.withCatalogName(pkgCfg.getPkgName())
							.withFunctionName(procedure_name)
							.withReturnValue()
							.returningResultSet(result_key,
								new RowMapper<DepartmentStatisticT>() {
									@Override
									public DepartmentStatisticT mapRow(ResultSet rs, int rowNum) throws SQLException {
										DepartmentStatisticT obj = 
											new DepartmentStatisticT((DepartmentDescrT)rs.getObject(1, typesMap),
																	rs.getLong(2),
																	rs.getBigDecimal(3),
																	rs.getBigDecimal(4),
																	rs.getBigDecimal(5),
																	rs.getBigDecimal(5));
										return obj;
									}
								}
							)
							.declareParameters(
								new SqlParameter(param_dep_id, OracleTypes.INTEGER));
				
					jdbcCall.compile();
					return jdbcCall; 
				}
			});	
		
		Map<String, Object> params =  
				Collections.singletonMap(param_dep_id, depId.isPresent() ? depId.get() : null);
		
		Map<String, Object> resVals = jdbcCall.execute(params);
		
		return (List<DepartmentStatisticT>)resVals.get(result_key);
	}
	
	protected abstract class GetDepartmentsProcedure extends StoredProcedure
	{
		protected final static String PROCEDURE = "GET_DEPARTMENTS";
		protected static final String param_location = "p_location_id";
		protected static final String param_departments = "p_departments";
		protected static final String param_names_list = "p_names_filter";
		
		protected GetDepartmentsProcedure(SqlParameter[] params)
		{
			final String strStmt = Joiner.on(".").join(pkgCfg.getPkgName(), PROCEDURE);
					
			setDataSource(jdbcTemplate.getDataSource());
			setFunction(false);
			setSql(strStmt);
			
			for (SqlParameter param : params)
				declareParameter(param);
			
			compile();
		}
	}
	
	protected class GetDepartmentsByLocationProcedure extends GetDepartmentsProcedure
	{
		GetDepartmentsByLocationProcedure()
		{
			super(new SqlParameter[] 
					{
					  new SqlParameter(param_location, OracleTypes.NUMBER),
					  new SqlOutParameter(param_departments, HrutilsDepartmentsT._SQL_TYPECODE, 
								 HrutilsDepartmentsT._SQL_NAME, new SqlReturnSqlData(HrutilsDepartmentsT.class))
					});
			compile();
		}
		
		List<HrUtilsDepartmentsEntryT> call(final BigDecimal locationId) throws SQLException
		{
			final Map<String, Object> params = Collections.singletonMap(param_location, locationId);
			Map<String, Object> outVals = execute(params);
			HrutilsDepartmentsT tbDepts = (HrutilsDepartmentsT)outVals.get(param_departments);
			return  Arrays.asList(tbDepts.getArray());
		}
	}
}
