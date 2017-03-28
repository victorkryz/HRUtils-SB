package victor.kryz.hr.sb;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import oracle.sql.ORAData;
import victor.kryz.hrutils.ents.RegionsEntryT;
import victor.kryz.hrutils.ents.RegionsT;
import victor.kryz.hrutils.ents.CountriesEntryT;


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
}		
