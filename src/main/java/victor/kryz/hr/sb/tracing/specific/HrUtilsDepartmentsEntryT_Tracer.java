package victor.kryz.hr.sb.tracing.specific;

import java.sql.SQLException;

import org.slf4j.Logger;

import victor.kryz.hr.sb.tracing.ObjectTracer;
import victor.kryz.hrutils.ents.HrUtilsDepartmentsEntryT;

public class HrUtilsDepartmentsEntryT_Tracer implements ObjectTracer<HrUtilsDepartmentsEntryT>{
	@Override
	public void trace(final HrUtilsDepartmentsEntryT item, Logger log) throws SQLException {
		final String template = "%s (id: %s, manager id: %s)";
		log.info(String.format(template, 
				 				item.getDepartment().getDepName(),
				 				item.getDepartment().getDepId(),
				 				item.getManagerId()));
	}
}
