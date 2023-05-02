package es.dao.sportiva

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer

@SpringBootApplication
class SportivaApplication

fun main(args: Array<String>) {
	runApplication<SportivaApplication>(*args)
	//spring.jpa.hibernate.ddl-auto=create
	// server.port=7290
}
