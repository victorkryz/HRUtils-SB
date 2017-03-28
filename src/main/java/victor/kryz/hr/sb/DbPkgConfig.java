package victor.kryz.hr.sb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value="classpath:/META-INF/spring/hr-utils.pkg.properties")
public class DbPkgConfig {

	@Value("${schema.name}")
	String schemaName;
	
	@Value("${pkg.name}")
	String pkgName;
	
	public String getSchemaName() {
		return schemaName;
	}

	public String getPkgName() {
		return pkgName;
	}
}
