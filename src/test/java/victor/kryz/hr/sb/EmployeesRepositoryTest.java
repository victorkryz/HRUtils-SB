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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.base.Joiner;

import victor.kryz.hr.sb.ents.EmployeeBriefEntryT;
import victor.kryz.hr.sb.repositories.EmployeesRepository;
import victor.kryz.hr.sb.repositories.RegionsRepository;
import victor.kryz.hr.sb.tracing.GetTracer;
import victor.kryz.hr.sb.tracing.ObjectTracer;
import victor.kryz.hr.sb.tracing.Tracer;
import victor.kryz.hrutils.ents.HrUtilsJobHistoryEntryT;
import victor.kryz.hrutils.ents.HrUtilsRegionsEntryT;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeesRepositoryTest 
{
	private static final Logger LOG = LoggerFactory.getLogger(EmployeesRepositoryTest.class);
	
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
		
		ObjectTracer<EmployeeBriefEntryT> emplTracer = GetTracer.getForClass(EmployeeBriefEntryT.class);
		ObjectTracer<HrUtilsJobHistoryEntryT> jsTracer = GetTracer.getForClass(HrUtilsJobHistoryEntryT.class);
		
		for (EmployeeBriefEntryT item :  ents)
		{
			emplTracer.trace(item, LOG);
			
			List<HrUtilsJobHistoryEntryT> jhs = emplRep.getJobHistory(item.getEmplId());
			
			assert(jhs.size() != 0);
			
			for (HrUtilsJobHistoryEntryT jh : jhs)
			{
				final String strLine = Joiner.on(" ").join(" -", jsTracer.getString(jh));
				jsTracer.putLn(strLine, LOG);
			}
			
			emplTracer.putLn("----------------------", LOG);
		}
	}
}
