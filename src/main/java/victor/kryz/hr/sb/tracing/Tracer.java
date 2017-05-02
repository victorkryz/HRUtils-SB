/**
 * HRUtils-SB
 *
 * @author Victor Kryzhanivskyi
 */
package victor.kryz.hr.sb.tracing;

import java.sql.SQLException;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tracer {
	
	private static final Logger LOG = LoggerFactory.getLogger(Tracer.class);
	
	public static <T, S extends ObjectTracer<T>> void traceObject(Collection<T> items, S tracer) throws SQLException, ExecutionException
	{
		for (T item : items)
			traceItem(item, tracer);
	}
	
	public static <T, S extends ObjectTracer<T>> void traceObject(T[] items, S tracer) throws SQLException, ExecutionException
	{
		for (T item : items)
			traceItem(item, tracer);
	}
	
	public static <T, S extends ObjectTracer<T>> void traceItem(T item, S tracer)
	{
		//& LOG.info("-------------------");
		try {
			tracer.trace(item, LOG);
		} catch (SQLException e) {
			LOG.error(e.getMessage());
		}
	}
}
