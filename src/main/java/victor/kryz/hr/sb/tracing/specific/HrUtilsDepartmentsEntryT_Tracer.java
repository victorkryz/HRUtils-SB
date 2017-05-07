/**
 * Project "HRUtils-SB"
 *
 * @author Victor Kryzhanivskyi
 */
package victor.kryz.hr.sb.tracing.specific;

import java.sql.SQLException;

import org.slf4j.Logger;

import victor.kryz.hr.sb.tracing.ObjectTracer;
import victor.kryz.hrutils.generated.ents.HrUtilsDepartmentsEntryT;

public class HrUtilsDepartmentsEntryT_Tracer implements ObjectTracer<HrUtilsDepartmentsEntryT>{
	@Override
	public String getString(final HrUtilsDepartmentsEntryT item) throws SQLException {
		final String template = "%s (id: %s, manager id: %s)";
		return String.format(template, 
				 				item.getDepartment().getDepName(),
				 				item.getDepartment().getDepId(),
				 				item.getManagerId());
	}
}
