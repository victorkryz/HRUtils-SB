package victor.kryz.hr.sb;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
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

import victor.kryz.hr.sb.repositories.LocationsRepository;
import victor.kryz.hr.sb.tracing.GetTracer;
import victor.kryz.hr.sb.tracing.Tracer;
import victor.kryz.hr.sb.utils.ThrowableWrapper;
import victor.kryz.hrutils.ents.HrUtilsDepartmentsEntryT;
import victor.kryz.hrutils.ents.HrUtilsLocationsEntryT;
import victor.kryz.hrutils.ents.HrUtilsRegionsEntryT;

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
	public void getLocations() throws SQLException, ExecutionException 
	{
		final String[] pattern  = new String[] {"London", "Oxford", "Stretford"};
		List<HrUtilsLocationsEntryT> ents = checkByPattern("UK", Arrays.asList(pattern));
		
		Tracer.traceObject(ents, GetTracer.getForClass(HrUtilsLocationsEntryT.class));
	}
	
	@Test
	public void getLocationsByNames() throws SQLException, ExecutionException 
	{
		List<String> namesFilterList = 
				Arrays.asList(new String[] {"California", "Yukon", "Sao Paulo", "New Jersey"});
		List<HrUtilsLocationsEntryT> ents = repLocations.findLocationsByNames(namesFilterList);
		
		assertTrue(namesFilterList.size()== ents.size());
		checkResult(namesFilterList, ents);
		
		Tracer.traceObject(ents, GetTracer.getForClass(HrUtilsLocationsEntryT.class));
	}
	
	private void checkResult(List<String> namesFilterList, List<HrUtilsLocationsEntryT> ents)
	{
		namesFilterList.sort((item1, item2) -> item1.compareTo(item2));
		
		ents.sort((HrUtilsLocationsEntryT item1, HrUtilsLocationsEntryT item2) ->
						ThrowableWrapper.wrap(()->item1.getStateProvince())
						.compareTo(wrapThrowable(()->item2.getStateProvince())));
		
		List<String> checkList = 
				ents.stream()
					.map(item -> ThrowableWrapper.wrap(()-> item.getStateProvince()))
						.collect(Collectors.toList());
			
		assertArrayEquals(checkList.toArray(), namesFilterList.toArray());
	}
	
	private List<HrUtilsLocationsEntryT> checkByPattern(String countryId, 
														List<String> pattern) throws SQLException, ExecutionException
	{	
		List<HrUtilsLocationsEntryT> ents = repLocations.findLocationsByCountryId(countryId);
			
		List<String> checkList = ents.stream()
				.map(item -> ThrowableWrapper.wrap(()-> item.getCity()))
				.collect(Collectors.toList());	
		
		assert(checkList.containsAll(pattern));
		return ents;
	}
}
