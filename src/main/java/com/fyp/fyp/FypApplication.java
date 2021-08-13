package com.fyp.fyp;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;

/**/import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**/import java.sql.SQLException;

@SpringBootApplication
@EnableWebMvc
public class FypApplication {

	public static void main(String[] args) {
		SpringApplication.run(FypApplication.class, args);
	}

}
