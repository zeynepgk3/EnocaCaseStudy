package com.example.enoca_studycase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class EnocaStudycaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnocaStudycaseApplication.class, args);
	}

}
