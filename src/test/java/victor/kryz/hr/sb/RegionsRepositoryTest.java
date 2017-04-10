package victor.kryz.hr.sb;

import static org.junit.Assert.*;

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

import victor.kryz.hr.sb.repositories.RegionsRepository;
import victor.kryz.hr.sb.tracing.GetTracer;
import victor.kryz.hr.sb.tracing.Tracer;
import victor.kryz.hr.sb.tracing.specific.HrUtilsRegionsEntryT_Tracer;
import victor.kryz.hrutils.ents.HrUtilsRegionsEntryT;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegionsRepositoryTest 
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
	RegionsRepository regRep;
	
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
	public void getRegions() throws SQLException 
	{
		List<String> namesFilterList = Arrays.asList(new String[] {"Asia", "Europe"}); 
		HrUtilsRegionsEntryT[] regions = regRep.findRegions(Optional.of(namesFilterList));
		
		checkResult(namesFilterList, regions);
	}
	
	@Test
	public void getRegions2() throws SQLException 
	{
		List<String> namesFilterList = Arrays.asList(new String[] {"Middle East and Africa", "Americas"}); 
		HrUtilsRegionsEntryT[] regions = regRep.findRegions(Optional.of(namesFilterList));
		
		checkResult(namesFilterList, regions);
	}
	
	private void checkResult(List<String> namesFilterList, HrUtilsRegionsEntryT[] regions)
	{
		namesFilterList.sort((item1, item2) -> item1.compareTo(item2));
		
		List<String> resList = null;
		{
			List<HrUtilsRegionsEntryT> sortedItems = Arrays.asList(regions);
			sortedItems.sort((HrUtilsRegionsEntryT item1, HrUtilsRegionsEntryT item2) ->
							wrapThrowable(()->item1.getRegionName())
							.compareTo(wrapThrowable(()->item2.getRegionName())));
			resList = 
					sortedItems.stream()
						.map(item -> wrapThrowable(()-> item.getRegionName()))
							.collect(Collectors.toList());
		}	
			
		assertArrayEquals(resList.toArray(), namesFilterList.toArray());
	}
	
	

	@Test
	public void trace() throws SQLException, ExecutionException 
	{
		HrUtilsRegionsEntryT[] regs = regRep.findRegions(Optional.empty());
		Tracer.traceObject(regs, GetTracer.getForClass(HrUtilsRegionsEntryT.class));
	}
	
	/*
	 * @Test
	public void getRegions() throws SQLException 
	{
		List<String> namesFilterList = Arrays.asList(new String[] {"Asia", "Europe"}); 
		RegionsEntryT[] regs = regRep.getRegions(namesFilterList);
		
		namesFilterList.sort((item1, item2) -> item1.compareTo(item2));
		
		List<RegionsEntryT> regList = Arrays.asList(regs);
		regList.sort((RegionsEntryT item1, RegionsEntryT item2) -> 
		{ 
			try
			{
				return item1.getRegionName().compareTo(item2.getRegionName());
			}
			catch (SQLException e){
		        throw new RuntimeException(e);
			}
		} );
		
		List<String> resList = regList.stream().map(it -> 
		{
			try
			{
				return it.getRegionName();
			}
			catch (SQLException e){
		        throw new RuntimeException(e);
			}
		}).collect(Collectors.toList());
		
		assertArrayEquals(resList.toArray(), namesFilterList.toArray());
		
	}
	 */
}
