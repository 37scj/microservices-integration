package br.com.fiap;

import com.google.common.base.Predicates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableSwagger2
@SpringBootApplication
public class DroneProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DroneProducerApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error")))
                .paths(Predicates.not(PathSelectors.regex("/actuator.*")))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfo(
                        "Drone API",
                        "Sensors Control AMQP",
                        "1.0",
                        "Terms of service",
                        new Contact("GaLuAl", "https://github.com/37scj/", ""),
                        "License of API", "API license URL", Collections.emptyList())
                );
    }

}
