package victor.kryz.hr.sb.tracing;

import java.sql.SQLException;
import org.slf4j.Logger;

public interface ObjectTracer<T> 
{
	String getString(final T item) throws SQLException;
	
	default void trace(final T item, Logger log) throws SQLException {
		log.info(getString(item));
	}
	default void putLn(String strLine, Logger log ){
		log.info(strLine);
	}
};
