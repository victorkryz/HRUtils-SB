package victor.kryz.hr.sb.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import javax.sql.DataSource;
import oracle.jdbc.OracleConnection;

public class NativeJdbcConnection {
	
	public static OracleConnection obtainOracleConnection(DataSource ds) throws SQLException
	{
		Connection conn = ds.getConnection();
    	DatabaseMetaData meta = conn.getMetaData();
    	return (OracleConnection)meta.getConnection();
	}
}
