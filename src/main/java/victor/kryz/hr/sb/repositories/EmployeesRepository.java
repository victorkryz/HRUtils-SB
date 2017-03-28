package victor.kryz.hr.sb.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeesRepository {
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	EmployeesRepository() {
	}
	
	void test01(){
		int i = jdbcTemplate.queryForObject("select 1 from dual", Integer.class);
		System.out.println(i);
	}
	
	void test02()
	{
		List<Map<String, Object>> selList = jdbcTemplate.queryForList("select * from EMPLOYEES_CONSOLIDATED_VIEW");
		
		for ( Map<String, Object> entry : selList )
		{
			System.out.println(entry.toString());
		}
	}
}
