package com.likelion.tutorial.config;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
ModelMapper modelMapper=new ModelMapper();
modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
return modelMapper;
    }
}
