package datnnhom12api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class DatnNhom12ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatnNhom12ApiApplication.class, args);
	}

}
