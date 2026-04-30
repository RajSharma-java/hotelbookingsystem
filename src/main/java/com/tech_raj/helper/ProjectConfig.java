package com.tech_raj.helper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ProjectConfig {

    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
