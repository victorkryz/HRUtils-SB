/**
 * Project "HRUtils-SB"
 *
 * @author Victor Kryzhanivskyi
 */
package victor.kryz.hr.sb.tracing.specific;

import java.sql.SQLException;
import org.slf4j.Logger;

import victor.kryz.hr.sb.tracing.ObjectTracer;
import victor.kryz.hrutils.generated.ents.HrUtilsLocationsEntryT;

public class HrUtilsLocationsEntryT_Tracer implements ObjectTracer<HrUtilsLocationsEntryT> {
	@Override
	public String getString(final HrUtilsLocationsEntryT item) throws SQLException {		
		 final String template = "%s, %s, %s, %s (id: %s)";
		 return String.format(template, 
				 				item.getCity(),
				 				item.getStateProvince(),
				 				item.getStreetAddress(),
				 				item.getPostalCode(),
				 				item.getId().toString());
	}
}
