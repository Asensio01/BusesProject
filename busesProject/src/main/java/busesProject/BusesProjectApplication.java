package busesProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "busesProject.models") // Detecta las entidades JPA
@EnableJpaRepositories(basePackages = "busesProject.repositories") // Activa los repositorios JPA
public class BusesProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusesProjectApplication.class, args);
	}
}
