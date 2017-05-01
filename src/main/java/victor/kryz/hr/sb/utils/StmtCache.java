package victor.kryz.hr.sb.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import oracle.jdbc.OracleCallableStatement;
import victor.kryz.hr.sb.repositories.EmployeesRepository;

@Component
public class StmtCache<T>
{
	private static final Logger LOG = LoggerFactory.getLogger(StmtCache.class);
	
	private Cache<String, T> spCache;
	
	public StmtCache(){
		this(50);
	}
	
	public StmtCache(int maxSize)
	{
		spCache = CacheBuilder.newBuilder()
		    	.maximumSize(maxSize)
		    	.expireAfterAccess(1, TimeUnit.MINUTES)
		    	.removalListener(
		    				new RemovalListener<String, T>() {
		    					 public void onRemoval(RemovalNotification<String, T> notification) 
		    					 {
		    						 final T val = notification.getValue();
		    						 final Class<?> clazz = val.getClass();
		    						 LOG.debug("Statement " + clazz.getName() + "removed from cache");
		    					 }
		    				}
		    			)
		    	.build();
		
	}
	
	@PreDestroy
	public void destory(){
		spCache.cleanUp();
	}
	
	
	public synchronized T getStmt(final String strKey, Callable<T> factory)
	{
	   T stmt = null;
		
		try {
			stmt = spCache.get(strKey, factory); 
		} catch (ExecutionException e) {
			throw new RuntimeException(e);
		}
		return stmt;
	}
}


