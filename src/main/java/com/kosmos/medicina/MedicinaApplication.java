package com.kosmos.medicina;

import java.util.Date;
import java.util.TimeZone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@SpringBootConfiguration
@ComponentScan
@Slf4j
public class MedicinaApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(MedicinaApplication.class, args);
	}

	@jakarta.annotation.PostConstruct
    public void init(){
		final String defaultTimeZone = "America/Mexico_City";
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZone));
		log.info("Default Time Zone: " + defaultTimeZone);
		log.info("Date: " + new Date());
    }
	
	@Override
	public void run(String... args) {
		log.info("RUN API REST SERVICES APP");
	}

}
