/**
 * HRUtils-SB
 *
 * @author Victor Kryzhanivskyi
 */
package victor.kryz.hr.sb.repositories;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

@Repository
public class InfoRepository {
	
	private static final Logger LOG = LoggerFactory.getLogger(InfoRepository.class);
	
	private static final int E_UNSUITABLE_SERVER_VERSION = 20100;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public void checkDbServerVersion() throws IOException, SQLException
	{
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream s = classLoader.getResourceAsStream("check-db-srv-ver.sql");
		CallableStatement stmt = null;
		InputStreamReader reader = null;
		
		try
		{
			reader = new InputStreamReader(s, Charsets.UTF_8);
			final String pl = CharStreams.toString(reader);
		
			Connection conn = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
			
			try {
				conn.prepareCall(pl).execute();
			}
			catch(SQLException e)
			{
				final int errCode =  e.getErrorCode();
				if ( errCode == E_UNSUITABLE_SERVER_VERSION )
					LOG.info("Oracle server version is lower than required!\n"
							+ "See topic 'About Using PL/SQL Types' of 'Oracle Database JDBC Developer's Guide' (http://docs.oracle.com/database/122/JJDBC/JDBC-reference-information.htm#GUID-E77C2AE8-E22B-48BF-A4CB-010CBC8FE7C2).");
				throw e;
			}
		}
		finally {
			if ( null != stmt)
				stmt.close();
			if ( null != reader)
				reader.close();
			s.close();
		}
	}
}
