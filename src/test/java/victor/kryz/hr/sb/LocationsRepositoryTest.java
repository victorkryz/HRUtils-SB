package victor.kryz.hr.sb;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import victor.kryz.hr.sb.repositories.LocationsRepository;
import victor.kryz.hrutils.ents.LocationsEntryT;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationsRepositoryTest 
{
	@FunctionalInterface
	protected interface VoidParamFnk<R> {
		R method() throws SQLException;
	}
	
	String wrapThrowable(VoidParamFnk<String> fnk)
	{
		try
		{
			return fnk.method();
		}
		catch (SQLException e){
	        throw new RuntimeException(e);
		}
	}
	
	Traccer traccer;
	
	@Autowired
	LocationsRepository repLocations;
	

	@Before
	public void setUp() throws Exception {
		traccer = new Traccer();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void getLocations() throws SQLException 
	{
		LocationsEntryT[] ents = repLocations.getLocations("UK"); 
		checkResult(ents);
	}
	
	@Test
	public void trace() throws SQLException 
	{
		LocationsEntryT[] ents = repLocations.getLocations("UK");
		traccer.trace(ents);
	}
	
	private void checkResult(LocationsEntryT[] ents)
	{
	}
}
