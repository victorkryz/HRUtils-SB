package victor.kryz.hr.sb;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import victor.kryz.hr.sb.repositories.InfoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests 
{
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationTests.class);
	
	@Autowired
	InfoRepository repInfo;
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void checkOracleServerVersion() throws IOException, SQLException {
		repInfo.checkDbServerVersion();
		LOG.info("Oracle server version is suitable!");
	}
}
