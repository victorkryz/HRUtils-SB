/**
 * Project "HRUtils-SB"
 *
 * @author Victor Kryzhanivskyi
 */
package victor.kryz.hr.sb.tracing.specific;

import java.sql.SQLException;

import org.slf4j.Logger;
import victor.kryz.hr.sb.ents.DepartmentStatisticT;
import victor.kryz.hr.sb.tracing.ObjectTracer;

public class DepartmentStatisticT_Tracer implements ObjectTracer<DepartmentStatisticT> {
	@Override
	public String getString(DepartmentStatisticT item) throws SQLException {
		
		final String template = "%s (id: %s) salary statistics: max - %s, min - %s, avg - %s, total - %s";
		return String.format(template,
				 				item.getDeptDescr().getDepName(),
				 				item.getDeptDescr().getDepId().toString(),
				 				item.getSalMax().toString(),
				 				item.getSalMin().toString(),
				 				item.getSalAvg().toString(),
				 				item.getSalTotal().toString());
	}
}
