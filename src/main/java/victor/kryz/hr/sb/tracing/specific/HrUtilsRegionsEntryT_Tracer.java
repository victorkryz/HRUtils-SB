/**
 * HRUtils-SB
 *
 * @author Victor Kryzhanivskyi
 */
package victor.kryz.hr.sb.tracing.specific;

import java.sql.SQLException;
import org.slf4j.Logger;

import victor.kryz.hr.sb.tracing.ObjectTracer;
import victor.kryz.hrutils.generated.ents.HrUtilsRegionsEntryT;

public class HrUtilsRegionsEntryT_Tracer implements ObjectTracer<HrUtilsRegionsEntryT> {
	@Override
	public String getString(final HrUtilsRegionsEntryT item) throws SQLException {
		 final String template = "%s (id: %s)";
		 return String.format(template, item.getRegionName(), item.getRegionId().toString());
	}
}
