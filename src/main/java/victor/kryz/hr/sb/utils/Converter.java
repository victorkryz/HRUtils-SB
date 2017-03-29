package victor.kryz.hr.sb.utils;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import victor.kryz.hrutils.ents.StringListT;

public class Converter {

	public static List<String> objListToList(final StringListT src) throws SQLException {
		return Arrays.asList(src.getArray());
	}
	
	public static StringListT listToObjList(final List<String> src) {
		return new StringListT(src.toArray(new String[src.size()]));
	}
}
