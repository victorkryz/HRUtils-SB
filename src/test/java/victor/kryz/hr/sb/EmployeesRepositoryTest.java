package victor.kryz.hr.sb;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
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

import victor.kryz.hr.sb.ents.EmployeeBriefEntryT;
import victor.kryz.hr.sb.repositories.EmployeesRepository;
import victor.kryz.hr.sb.repositories.RegionsRepository;
import victor.kryz.hr.sb.tracing.GetTracer;
import victor.kryz.hr.sb.tracing.Tracer;
import victor.kryz.hrutils.ents.HrUtilsRegionsEntryT;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeesRepositoryTest 
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
	
	@Autowired
	EmployeesRepository emplRep;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void contextLoads() {
	}
	
	
	@Test
	public void getEmployeesWithJobHistory() throws SQLException, ExecutionException 
	{
		List<EmployeeBriefEntryT> ents = emplRep.getEmployeesWithJobHistory(Optional.empty());
		Tracer.traceObject(ents, GetTracer.getForClass(EmployeeBriefEntryT.class));
	}
}
