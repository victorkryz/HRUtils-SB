package victor.kryz.hr.sb;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import victor.kryz.hr.sb.repositories.EmployeesRepository;
import victor.kryz.hr.sb.repositories.RegionsRepository;
import victor.kryz.hrutils.ents.RegionsEntryT;

import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

@SpringBootApplication
public class Application  implements CommandLineRunner 
{
	@Autowired
	DataSource dataSource;
	
	@Autowired
	EmployeesRepository emplRep;
	
	@Autowired
	RegionsRepository regRep;
	
	
	Traccer traccer = new Traccer();
	
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
//		SpringApplication.run("classpath:/META-INF/spring/applicationContext.xml", args);
	}
	
	@Override
    public void run(String... args) throws Exception {
    	
    	System.out.println("DATASOURCE = " + dataSource);
    	
//    	emplRep.test01();
//    	emplRep.test02();
    	
//    	String[] names = {"bebeb", "ssss"};
//    	List<String> filter = Arrays.asList(names);
//    	RegionsEntryT[] regs = regRep.getRegions(null);
//    	traccer.trace(regs);
    	
//    	regRep.test1();
//    	regRep.test4();
//    	regRep.test3();
    	
//        BasicDataSource newds = (BasicDataSource)dataSource;
//        System.out.println("BasicDataSource = " + newds.getInitialSize());
    	
    	 System.out.println("---end run---");
    }
}
