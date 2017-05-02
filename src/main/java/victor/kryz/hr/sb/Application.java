/**
 * HRUtils-SB
 *
 * @author Victor Kryzhanivskyi
 */
package victor.kryz.hr.sb;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Locale;

@SpringBootApplication
public class Application  implements CommandLineRunner 
{
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
    public void run(String... args) throws Exception {
 	  Locale loc = new Locale("en","US");	
      Locale.setDefault(loc);	 
      System.out.println("---end run---");
    }
}
