package pl.edu.pw.elka.pap.z16.almostjira;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlmostJiraApplication {

	public static void main(String[] args) {
		System.out.println("TUTAJ: >" + System.getenv("DB_USER") + "< haslo: >" + System.getenv("DB_PASSWORD") + "<");
		SpringApplication.run(AlmostJiraApplication.class, args);
	}

}
