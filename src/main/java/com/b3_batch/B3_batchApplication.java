package com.b3_batch;

import io.swagger.v3.oas.models.info.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
public class B3_batchApplication {

    public static void main(String[] args) {
        SpringApplication.run(B3_batchApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
        return new OpenAPI().info(new Info().title("B3 Batch API")
                .version(appVersion)
                .description("This is a B3 restful api - a library for OpenAPI 3 with spring boot.")
                .termsOfService("http://swagger.io/terms/")
                .contact(new Contact().name("Saulo de Tarso").url("https://www.linkedin.com/in/saulo-de-tarso-b5217932/").email("osaulo_tarso@hotmail.com"))
                .license(new License().name("Apache 2.0")
                        .url("http://springdoc.org")));
    }

}
