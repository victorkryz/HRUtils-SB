package victor.kryz.hr.sb;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
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

import victor.kryz.hr.sb.repositories.CountriesRepository;
import victor.kryz.hr.sb.repositories.RegionsRepository;
import victor.kryz.hr.sb.tracing.GetTracer;
import victor.kryz.hr.sb.tracing.Tracer;
import victor.kryz.hr.sb.utils.ThrowableWrapper;
import victor.kryz.hrutils.ents.HrUtilsCountriesEntryT;
import victor.kryz.hrutils.ents.HrUtilsRegionsEntryT;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CountriesRepositoryTest 
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
	RegionsRepository repRegions;
	
	@Autowired
	CountriesRepository repCountries;
	
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
	public void getCountries() throws SQLException 
	{
		final BigDecimal regId = obtainRegId("Europe");
		HrUtilsCountriesEntryT[] ents = repCountries.findCountriesByRegionId(regId); 
		checkResult(ents);
	}
	
	@Test
	public void trace() throws SQLException, ExecutionException 
	{
		final BigDecimal regId = obtainRegId("Europe");
		HrUtilsCountriesEntryT[] ents = repCountries.findCountriesByRegionId(regId);
		Tracer.traceObject(ents, GetTracer.getForClass(HrUtilsCountriesEntryT.class));
	}
	
	private BigDecimal obtainRegId(String strRegName) throws SQLException
	{
		List<String> namesFilterList = Arrays.asList(new String[] {strRegName}); 
		HrUtilsRegionsEntryT[] regions = repRegions.findRegions(Optional.of(namesFilterList));
		
		assertTrue(regions.length == 1);
		assertTrue(0 == regions[0].getRegionName().compareTo(strRegName));
		
		return regions[0].getRegionId();
	}
	
	private void checkResult(HrUtilsCountriesEntryT[] ents)
	{
		List<String> resList = null;
		{
			List<HrUtilsCountriesEntryT> sortedItems = Arrays.asList(ents);
			sortedItems.sort((HrUtilsCountriesEntryT item1, HrUtilsCountriesEntryT item2) ->
								ThrowableWrapper.wrap(()->item1.getName())
									.compareTo(wrapThrowable(()->item2.getName())));
			resList = 
					sortedItems.stream()
						.map(item -> wrapThrowable(()-> item.getName()))
							.collect(Collectors.toList());
		}	
	}
}
