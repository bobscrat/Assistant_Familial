package fr.acdo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AcdoApplication {
	private static final Logger logger = LoggerFactory.getLogger(AcdoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AcdoApplication.class, args);
		logger.info("***--Application Started--***");
	}
}
