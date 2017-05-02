/**
 * HRUtils-SB
 *
 * @author Victor Kryzhanivskyi
 */
package victor.kryz.hr.sb.tracing.specific;

import java.sql.SQLException;

import org.slf4j.Logger;

import victor.kryz.hr.sb.ents.EmployeeBriefEntryT;
import victor.kryz.hr.sb.tracing.ObjectTracer;

public class EmployeeBriefEntryT_Tracer implements ObjectTracer<EmployeeBriefEntryT> {
	@Override
	public String getString(final EmployeeBriefEntryT item) throws SQLException {
		 final String template = "%s %s (id: %s, department id: %s )";
		 return String.format(template, item.getFirstName(),
				 				item.getLastName(), item.getEmplId().toString(), 
				 				item.getDepId().toString());
	}
}
