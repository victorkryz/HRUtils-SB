package victor.kryz.hr.sb.repositories;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.support.oracle.SqlReturnSqlData;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.internal.OracleTypes;
import oracle.sql.ORAData;
import victor.kryz.hr.sb.DbPkgConfig;
import victor.kryz.hrutils.ents.RegionsEntryT;
import victor.kryz.hrutils.ents.RegionsT;
import victor.kryz.hrutils.ents.StringListT;

import org.springframework.jdbc.core.simple.SimpleJdbcCall;

@Repository
public class LocationsRepository 
{
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Autowired
	private DbPkgConfig pkgCfg;
	
	LocationsRepository() {
	}
}
