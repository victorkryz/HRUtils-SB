package victor.kryz.hr.sb;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import victor.kryz.hr.sb.repositories.RegionsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests 
{
	@Autowired
	RegionsRepository regRep;
	
	@Test
	public void contextLoads() {
	}
}
