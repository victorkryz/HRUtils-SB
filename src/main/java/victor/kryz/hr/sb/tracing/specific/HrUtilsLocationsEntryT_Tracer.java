package victor.kryz.hr.sb.tracing.specific;

import java.sql.SQLException;
import org.slf4j.Logger;

import victor.kryz.hr.sb.tracing.ObjectTracer;
import victor.kryz.hrutils.ents.HrUtilsLocationsEntryT;

public class HrUtilsLocationsEntryT_Tracer implements ObjectTracer<HrUtilsLocationsEntryT> {
	@Override
	public void trace(final HrUtilsLocationsEntryT item, Logger log) throws SQLException {		
		 final String template = "%s, %s, %s, %s (id: %s)";
		 log.info(String.format(template, 
				 				item.getCity(),
				 				item.getStateProvince(),
				 				item.getStreetAddress(),
				 				item.getPostalCode(),
				 				item.getId().toString()));
	}
}
