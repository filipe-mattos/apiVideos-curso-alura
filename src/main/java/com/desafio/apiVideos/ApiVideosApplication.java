package com.desafio.apiVideos;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ApiVideosApplication {

	//Adicionando um regra para se caso o modelMapper encontre um valor nulo o mesmo pular esse valor
	@Bean
	public ModelMapper modelMapper(){
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		return modelMapper;
	}


	public static void main(String[] args) {
		SpringApplication.run(ApiVideosApplication.class, args);
	}

}
