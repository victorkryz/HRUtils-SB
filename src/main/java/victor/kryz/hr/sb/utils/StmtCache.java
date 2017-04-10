package victor.kryz.hr.sb.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

@Component
public class StmtCache<T>
{
	private Cache<String, T> spCache;
	
	public StmtCache(){
		this(50);
	}
	
	public StmtCache(int maxSize)
	{
		spCache = CacheBuilder.newBuilder()
		    	.maximumSize(maxSize)
		    	.expireAfterAccess(5, TimeUnit.MINUTES)
		    	.build();
		
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

