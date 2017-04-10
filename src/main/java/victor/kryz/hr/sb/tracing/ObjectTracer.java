package victor.kryz.hr.sb.tracing;

import java.sql.SQLException;
import org.slf4j.Logger;

public interface ObjectTracer<T> {
	void trace(final T item, Logger log) throws SQLException;
};
