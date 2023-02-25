package com.mycalories.caloriesrest.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EntityScan("com.mycalories.model2")
@ComponentScan(basePackages = "com.mycalories.model2")
@EnableJpaRepositories(basePackages = "com.mycalories.model2")
public class ModelConfiguration {


}
