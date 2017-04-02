package victor.kryz.hr.sb;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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

import victor.kryz.hr.sb.repositories.DepartmentsRepository;
import victor.kryz.hr.sb.repositories.LocationsRepository;
import victor.kryz.hrutils.ents.DepartmentsEntryT;
import victor.kryz.hrutils.ents.LocationsEntryT;
import victor.kryz.hrutils.ents.RegionsEntryT;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepartmentsRepositoryTest 
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
	DepartmentsRepository repDepartments;
	
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
	public void getDepartments() throws SQLException 
	{
		BigDecimal locationId = getLocation();
		
		DepartmentsEntryT[] ents = repDepartments.getDepartments(locationId);
		traccer.trace(ents);
	}
	
	@Test
	public void getDepartments2() throws SQLException 
	{
		List<String> namesFilterList = Arrays.asList(new String[] {"Administration", "Finance", "IT", 
																   "Public Relations", "Recruiting"});
		Map<String, DepartmentsEntryT> ents = repDepartments.getDepartments(namesFilterList);
		
		assertTrue(namesFilterList.size()== ents.size());
//		checkResult(namesFilterList, ents);
//		traccer.trace(ents);
	}
	
	@Test
	public void trace() throws SQLException 
	{
		BigDecimal locationId = getLocation();
		
		DepartmentsEntryT[] ents = repDepartments.getDepartments(locationId);
		traccer.trace(ents);
	}
	
	private BigDecimal getLocation() throws SQLException
	{
		List<String> namesFilterList = Arrays.asList(new String[] {"Washington"});
		LocationsEntryT[] ents = repLocations.getLocations(namesFilterList);
		
		assertTrue(ents.length == namesFilterList.size());
		
		assertEquals(ents[0].getStateProvince(), namesFilterList.get(0));
		
		return ents[0].getId();
	}
	
	private void checkResult(List<String> namesFilterList, DepartmentsEntryT[] ents)
	{
		namesFilterList.sort((item1, item2) -> item1.compareTo(item2));
		
		List<String> resList = null;
		{
//			List<DepartmentsEntryT> sortedItems = Arrays.asList(ents);
//			sortedItems.sort((LocationsEntryT item1, LocationsEntryT item2) ->
//							wrapThrowable(()->item1.getStateProvince())
//							.compareTo(wrapThrowable(()->item2.getStateProvince())));
//			resList = 
//					sortedItems.stream()
//						.map(item -> wrapThrowable(()-> item.getStateProvince()))
//							.collect(Collectors.toList());
		}	
			
		assertArrayEquals(resList.toArray(), namesFilterList.toArray());
	}
	
	private void checkResult(LocationsEntryT[] ents)
	{
	}
}