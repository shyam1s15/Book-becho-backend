package com.bookbecho;

import com.bookbecho.infrastructure.core.boot.BookBechoWebApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class BookBechoApplication {

	@Import({ BookBechoWebApplicationConfiguration.class })
	private static class Configuration {}

	private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
		return builder.sources(Configuration.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(BookBechoApplication.class, args);
	}

}
