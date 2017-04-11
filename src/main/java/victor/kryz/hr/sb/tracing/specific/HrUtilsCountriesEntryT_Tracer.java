package victor.kryz.hr.sb.tracing.specific;

import java.sql.SQLException;
import org.slf4j.Logger;

import victor.kryz.hr.sb.tracing.ObjectTracer;
import victor.kryz.hrutils.ents.HrUtilsCountriesEntryT;
import victor.kryz.hrutils.ents.HrUtilsRegionsEntryT;

public class HrUtilsCountriesEntryT_Tracer implements ObjectTracer<HrUtilsCountriesEntryT> {
	@Override
	public String getString(HrUtilsCountriesEntryT item) throws SQLException {
		 final String template = "%s (id: %s)";
		 return String.format(template, item.getName(), item.getId().toString());
	}
}
