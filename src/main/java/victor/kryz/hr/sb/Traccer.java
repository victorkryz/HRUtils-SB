package victor.kryz.hr.sb;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oracle.sql.ORAData;
import victor.kryz.hrutils.ents.RegionsEntryT;
import victor.kryz.hrutils.ents.RegionsT;
import victor.kryz.hrutils.ents.CountriesEntryT;
import victor.kryz.hrutils.ents.DepartmentsEntryT;
import victor.kryz.hrutils.ents.LocationsEntryT;


public class Traccer 
{
    private static final Logger LOG = LoggerFactory.getLogger(Traccer.class);
	
	<T extends RegionsEntryT> void trace(T[] items) throws SQLException
	{
		for ( T item : items)
		{
			LOG.info("-------------------");
			trace(item);
		}
	}
	
	
	<T extends CountriesEntryT> void trace(T[] items) throws SQLException
	{
		for ( T item : items)
		{
			LOG.info("-------------------");
			trace(item);
		}
	}
	
	<T extends LocationsEntryT> void trace(T[] items) throws SQLException
	{
		for ( T item : items)
		{
			LOG.info("-------------------");
			trace(item);
		}
	}
	
	<T extends DepartmentsEntryT> void trace(T[] items) throws SQLException
	{
		for ( T item : items)
		{
			LOG.info("-------------------");
			trace(item);
		}
	}
	
	void trace(RegionsEntryT item) throws SQLException
	{
		 final String template = "%s (id: %s)";
		 LOG.info(String.format(template, item.getRegionName(), item.getRegionId().toString())); 
	}
	
	void trace(CountriesEntryT item) throws SQLException
	{
		 final String template = "%s (id: %s)";
		 LOG.info(String.format(template, item.getName(), item.getId().toString())); 
	}
	
	void trace(LocationsEntryT item) throws SQLException
	{
		 final String template = "%s, %s, %s, %s (id: %s)";
		 LOG.info(String.format(template, 
				 				item.getCity(),
				 				item.getStateProvince(),
				 				item.getStreetAddress(),
				 				item.getPostalCode(),
				 				item.getId().toString())); 
	}
	
	void trace(DepartmentsEntryT item) throws SQLException
	{
		 final String template = "%s (id: %s, manager id: %s)";
		 LOG.info(String.format(template, 
				 				item.getDepartment().getDepName(),
				 				item.getDepartment().getDepId(),
				 				item.getManagerId()));
	}
}		
