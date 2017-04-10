package victor.kryz.hr.sb.tracing.specific;

import java.sql.SQLException;
import org.slf4j.Logger;

import victor.kryz.hr.sb.tracing.ObjectTracer;
import victor.kryz.hrutils.ents.HrUtilsRegionsEntryT;

public class HrUtilsRegionsEntryT_Tracer implements ObjectTracer<HrUtilsRegionsEntryT> {
	@Override
	public void trace(final HrUtilsRegionsEntryT item, Logger log) throws SQLException {
		 final String template = "%s (id: %s)";
		 log.info(String.format(template, item.getRegionName(), item.getRegionId().toString()));
		
	}
}
