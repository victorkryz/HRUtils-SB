package victor.kryz.hr.sb.repositories;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.support.oracle.SqlReturnSqlData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.google.common.base.Joiner;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import oracle.jdbc.OracleTypeMetaData;
import oracle.jdbc.internal.OracleTypes;
import victor.kryz.hr.sb.DbPkgConfig;
import victor.kryz.hr.sb.utils.Converter;
import victor.kryz.hrutils.ents.DepartmentsEntryT;
import victor.kryz.hrutils.ents.DepartmentsMapT;
import victor.kryz.hrutils.ents.DepartmentsT;
import victor.kryz.hrutils.ents.StringListT;

import org.springframework.jdbc.object.StoredProcedure;

@Repository
public class DepartmentsRepository 
{
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private DbPkgConfig pkgCfg;
	
	private Cache<String, GetDepartmentsProcedure> spCache;
	
	public DepartmentsRepository() 
	{
		spCache = CacheBuilder.newBuilder()
			    	.maximumSize(5)
			    	.expireAfterAccess(5, TimeUnit.MINUTES)
			    	.build(); 
	}
	
	public DepartmentsEntryT[] getDepartments(final BigDecimal locationId) throws SQLException
	{
		final String cacheKey = GetDepartmentsByLocationProcedure.class.getName();
		
		DepartmentsEntryT[] result = null;
		
		synchronized(this)  
		{
			GetDepartmentsByLocationProcedure proc = 
					(GetDepartmentsByLocationProcedure)getProcedureFromCache(cacheKey, 
											new Callable<GetDepartmentsProcedure>() {
												 @Override
												    public GetDepartmentsProcedure call()  {
												      return new GetDepartmentsByLocationProcedure();
												    }
												  });
			result = proc.call(locationId);
		}
		
		return result;
	}
	
	public Map<String, DepartmentsEntryT> getDepartments(final List<String> namesFilter) throws SQLException
	{
		final String cacheKey = GetDepartmentsByNamesProcedure.class.getName();
		
		Map<String, DepartmentsEntryT> result = null;
		
		synchronized(this)  
		{
			GetDepartmentsByNamesProcedure proc = 
					(GetDepartmentsByNamesProcedure)getProcedureFromCache(cacheKey, 
											new Callable<GetDepartmentsProcedure>() {
												 @Override
												    public GetDepartmentsProcedure call()  {
												      return new GetDepartmentsByNamesProcedure();
												    }
												  });
			result = proc.call(namesFilter);
		}
		
		return result;
	}
	
	protected GetDepartmentsProcedure getProcedureFromCache(final String strKey, Callable<GetDepartmentsProcedure> factory)
	{
	   GetDepartmentsProcedure proc = null;
		
		try {
			proc = spCache.get(strKey, factory); 
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
		return proc;
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
					  new SqlOutParameter(param_departments, DepartmentsT._SQL_TYPECODE, 
								 DepartmentsT._SQL_NAME, new SqlReturnSqlData(DepartmentsT.class))
					});
		}
		
		DepartmentsEntryT[] call(final BigDecimal locationId) throws SQLException
		{
			final Map<String, Object> params = Collections.singletonMap(param_location, locationId);
			Map<String, Object> outVals = execute(params);
			DepartmentsT tbDepts = (DepartmentsT)outVals.get(param_departments);
			return tbDepts.getArray();
		}
	}
	
	protected class GetDepartmentsByNamesProcedure extends GetDepartmentsProcedure
	{
		GetDepartmentsByNamesProcedure()
		{
			super(new SqlParameter[] 
					{
					  new SqlParameter(param_names_list, StringListT._SQL_TYPECODE, StringListT. _SQL_NAME),
					  new SqlOutParameter(param_departments, DepartmentsMapT._SQL_TYPECODE, 
								 DepartmentsMapT._SQL_NAME, new SqlReturnSqlData(DepartmentsMapT.class))
					});
		}
		
		Map<String, DepartmentsEntryT> call(final List<String> namesFilter) throws SQLException
		{
			
//			MoS Note 1364193.1
			
			class DepartmentsMapLT implements oracle.jdbc.OracleStruct
			{

				@Override
				public String getSQLTypeName() throws SQLException {
					// TODO Auto-generated method stub
					return "HR_UTILS.DEPARTMENTS_MAP_T";
				}

				@Override
				public Object[] getAttributes() throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public Object[] getAttributes(Map<String, Class<?>> map) throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public OracleTypeMetaData getOracleMetaData() throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}
				
			}
			
			
			
			final Map<String, Object> params = Collections.singletonMap(param_names_list, 
																		Converter.listToObjList(namesFilter));
			Map<String, Object> outVals = execute(params);
			DepartmentsMapT tbDepts = (DepartmentsMapT)outVals.get(param_departments);
			
			DepartmentsEntryT[] rg =  tbDepts.getArray();
			
			HashMap<String, DepartmentsEntryT> res = new HashMap<String, DepartmentsEntryT>();
			
//			sortedItems.stream()
//			.map(item -> wrapThrowable(()-> item.getStateProvince()))
//				.collect(Collectors.toList());

			
			
			return res;
		}
	}
}
