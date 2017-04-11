package victor.kryz.hr.sb.tracing.specific;

import java.sql.SQLException;
import java.text.DateFormat;

import victor.kryz.hr.sb.tracing.ObjectTracer;
import victor.kryz.hrutils.ents.HrUtilsJobHistoryEntryT;

public class HrUtilsJobHistoryEntryT_Tracer implements ObjectTracer<HrUtilsJobHistoryEntryT>{
	@Override
	public String getString(final HrUtilsJobHistoryEntryT item) throws SQLException {
		final String template = "Job: %s, (id: %s), department: %s (id: %s) (period: %s - %s)";
		
		DateFormat formatter = DateFormat.getDateTimeInstance();
		return String.format(template,
							item.getJobTitle(),
							item.getJobId(),
			 				item.getDepartment().getDepName(),
			 				item.getDepartment().getDepId().toString(),
			 				formatter.format(item.getStartDate()),
							formatter.format(item.getEndDate()));
	}
}

