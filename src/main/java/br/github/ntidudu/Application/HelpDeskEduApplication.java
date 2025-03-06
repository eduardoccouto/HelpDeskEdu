package br.github.ntidudu.Application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HelpDeskEduApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpDeskEduApplication.class, args);
	}

}
