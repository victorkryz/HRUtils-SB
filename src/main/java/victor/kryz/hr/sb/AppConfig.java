package victor.kryz.hr.sb;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
@PropertySource(value="classpath:/META-INF/spring/database.properties")
@ImportResource("classpath:/META-INF/spring/dsConfig.xml")
public class AppConfig{
    @Value("${database.driverClassName}")
    private String driverName;

    @Value("${database.url}")
    private String url;

    @Value("${database.username}")
    private String user;

    @Value("${database.password}")
    private String password;
    
    @Autowired
    DataSource dataSource;
    
    AppConfig() {
    }
    
//    @Bean
//    public DataSource dataSource() {
//        SimpleDriverDataSource simpleDriverDataSource = new SimpleDriverDataSource();
//        simpleDriverDataSource.setPassword(this.password);
//        simpleDriverDataSource.setUrl(this.url);
//        simpleDriverDataSource.setUsername(this.user);
//        simpleDriverDataSource.setDriverClass(oracle.jdbc.driver.OracleDriver.class);
//        return simpleDriverDataSource;
//    }
}
