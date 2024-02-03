package org.bedu.proyecto_escuela;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.standard.StandardDialect;


@SpringBootApplication
public class ProyectoEscuelaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoEscuelaApplication.class, args);
	}

}