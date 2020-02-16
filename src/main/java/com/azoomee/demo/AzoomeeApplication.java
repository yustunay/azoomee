package com.azoomee.demo;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@EnableScheduling
@SpringBootApplication
public class AzoomeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AzoomeeApplication.class, args);
	}

	@Autowired(required = true)
	public void configureJackson(ObjectMapper jackson2ObjectMapper) {
		jackson2ObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		jackson2ObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		jackson2ObjectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		jackson2ObjectMapper.setDateFormat(df);
	}

}
