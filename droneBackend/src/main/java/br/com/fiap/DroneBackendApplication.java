package br.com.fiap;

import com.google.common.base.Predicates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static java.util.Arrays.asList;

@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories
@EnableSwagger2
public class DroneBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(DroneBackendApplication.class, args);
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
                        "Drone Backend API",
                        "Sensors Control Backend",
                        "1.0",
                        "Terms of service",
                        new Contact("MBA 37SCJ", "https://github.com/37scj/microservices-integration", ""),
                        "Apache License 2.0", "http://www.apache.org/licenses/LICENSE-2.0",
                        Collections.emptyList())
                );
    }

}
