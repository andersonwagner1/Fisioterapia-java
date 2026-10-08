package br.com.fisioterapia.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "br.com.fisioterapia.usuario") // Substitua pelo nome do seu pacote
//@SpringBootApplication(
@SpringBootApplication(exclude={SecurityAutoConfiguration.class}) //desbiiltar a segurança
//@SpringBootApplication(exclude={SecurityConfiguration.class}) //desbiiltar a segurança

public class JogoApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(JogoApplication.class, args);
	}
}