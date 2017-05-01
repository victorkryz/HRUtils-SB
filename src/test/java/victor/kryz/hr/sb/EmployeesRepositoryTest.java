package victor.kryz.hr.sb;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;

import victor.kryz.hr.sb.ents.EmployeeBriefEntryT;
import victor.kryz.hr.sb.repositories.EmployeesRepository;
import victor.kryz.hr.sb.repositories.RegionsRepository;
import victor.kryz.hr.sb.tracing.GetTracer;
import victor.kryz.hr.sb.tracing.ObjectTracer;
import victor.kryz.hr.sb.tracing.Tracer;
import victor.kryz.hrutils.ents.EmployeeDescrT;
import victor.kryz.hrutils.ents.EmployeeSetT;
import victor.kryz.hrutils.ents.HrUtilsJobHistoryEntryT;
import victor.kryz.hrutils.ents.HrUtilsRegionsEntryT;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback(true)
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
	@Rollback(true)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public void addRemoveEmployees() throws SQLException, ExecutionException 
	{
		ArrayList<EmployeeDescrT> employees = new ArrayList<EmployeeDescrT>();
		
		employees.add(new EmployeeDescrT(null, "Forrest", "Gump", "fgumb", "575.111.222", BigDecimal.valueOf(60), 
						  			 	null, "IT_PROG", null, BigDecimal.valueOf(25520), BigDecimal.valueOf(103)));
		employees.add(new EmployeeDescrT(null, "Darth", "Vader", "dvader", "575.222.333", BigDecimal.valueOf(60), 
	  			 					 	null, "IT_PROG", null, BigDecimal.valueOf(250000), BigDecimal.valueOf(103)));
		
		emplRep.addEmployees(employees);
		
		List<BigDecimal> addedIds = new ArrayList<BigDecimal>();  
		
		for (EmployeeDescrT item :  employees) {
			assertNotNull(item.getEmplId());
			addedIds.add(item.getEmplId());
		}
		
		final int removedCount = emplRep.removeEmployees(addedIds);
		assertEquals(removedCount, addedIds.size());
	}
	
	@Test
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public void getEmployeesByFullName() throws SQLException, ExecutionException 
	{
		List<EmployeeBriefEntryT> ents = emplRep.findEmployeesWithJobHistory(Optional.empty());
		
		ArrayList<String> names = new ArrayList<String>();
		for (EmployeeBriefEntryT item :  ents) {
			names.add(Joiner.on(" ").join(item.getFirstName(), item.getLastName()));
		}
		
		List<BigDecimal> ids = emplRep.findEmployeesByFullName(names);
		
		assertEquals(ids.size(),ents.size());
	}
		
	
	@Test
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public void getEmployeesWithJobHistory() throws SQLException, ExecutionException 
	{
		List<EmployeeBriefEntryT> ents = emplRep.findEmployeesWithJobHistory(Optional.empty());
		
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
