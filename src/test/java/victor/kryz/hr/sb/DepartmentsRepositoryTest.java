package victor.kryz.hr.sb;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import victor.kryz.hr.sb.ents.DepartmentStatisticT;
import victor.kryz.hr.sb.repositories.DepartmentsRepository;
import victor.kryz.hr.sb.repositories.LocationsRepository;
import victor.kryz.hr.sb.tracing.GetTracer;
import victor.kryz.hr.sb.tracing.Tracer;
import victor.kryz.hr.sb.utils.ThrowableWrapper;
import victor.kryz.hrutils.generated.ents.HrUtilsDepartmentsEntryT;
import victor.kryz.hrutils.generated.ents.HrUtilsLocationsEntryT;
import victor.kryz.hrutils.generated.ents.HrUtilsRegionsEntryT;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentsRepositoryTest 
{
	@Autowired
	DepartmentsRepository repDepartments;
	@Autowired
	LocationsRepository repLocations;
	

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
	@Rollback(false)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED)
	public void getDepartmentsByLocation() throws SQLException, ExecutionException 
	{
		{
			final String[] pattern  = new String[] {"Accounting", "Construction", "Corporate Tax", "Executive"};	
			List<HrUtilsDepartmentsEntryT> ents = checkByPattern("Washington", Arrays.asList(pattern));
			Tracer.traceObject(ents, GetTracer.getForClass(HrUtilsDepartmentsEntryT.class));
		}	
		
		{
			final String[] pattern  = new String[] {"Sales"};
			List<HrUtilsDepartmentsEntryT> ents = checkByPattern("Oxford", Arrays.asList(pattern));
			Tracer.traceObject(ents, GetTracer.getForClass(HrUtilsDepartmentsEntryT.class));
		}
		
		{
			final String[] pattern  = new String[] {"IT"};
			List<HrUtilsDepartmentsEntryT> ents = checkByPattern("Texas", Arrays.asList(pattern));
			Tracer.traceObject(ents, GetTracer.getForClass(HrUtilsDepartmentsEntryT.class));
		}
	}
	
	@Test
	public void getDepartmentsStatistic() throws SQLException, ExecutionException 
	{
		List<DepartmentStatisticT> ents = repDepartments.getDepartmentsStat(Optional.empty());
		Tracer.traceObject(ents, GetTracer.getForClass(DepartmentStatisticT.class));
	}
	
	private List<HrUtilsDepartmentsEntryT> checkByPattern(String locationName, 
														  List<String> pattern) throws SQLException, ExecutionException
	{	
		BigDecimal locationId = getLocationId(locationName);
		List<HrUtilsDepartmentsEntryT> ents = repDepartments.findDepartmentsByLocationId(locationId);
		
		List<String> checkList = ents.stream()
				.map(item -> ThrowableWrapper.wrap(()-> item.getDepartment().getDepName()))
				.collect(Collectors.toList());	
		
		assert(checkList.containsAll(pattern));
		
		return ents;
	}
	
	private BigDecimal getLocationId(String strLocationName) throws SQLException
	{
		List<String> namesFilterList = Arrays.asList(new String[] {strLocationName});
		List<HrUtilsLocationsEntryT> ents = repLocations.findLocationsByNames(namesFilterList);
		
		assertTrue(ents.size() == namesFilterList.size());
		assertEquals(ents.get(0).getStateProvince(), namesFilterList.get(0));
		
		return ents.get(0).getId();
	}
}
