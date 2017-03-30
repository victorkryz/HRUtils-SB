package victor.kryz.hr.sb.utils;
import java.sql.SQLException;

public class ThrowableWrapper 
{
	@FunctionalInterface
	public interface VoidParamFnk<R> {
		R method() throws SQLException;
	}
	
	public static String wrap(VoidParamFnk<String> fnk)
	{
		try
		{
			return fnk.method();
		}
		catch (SQLException e){
	        throw new RuntimeException(e);
		}
	}
}
