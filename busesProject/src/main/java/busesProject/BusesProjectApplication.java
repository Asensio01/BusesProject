package busesProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "busesProject.models")
public class BusesProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusesProjectApplication.class, args);
	}

}
