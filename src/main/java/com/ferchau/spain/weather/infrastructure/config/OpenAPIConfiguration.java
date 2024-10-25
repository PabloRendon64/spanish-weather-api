package com.ferchau.spain.weather.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Pablo Rendon");
        myContact.setEmail("ing.pablo64@gmail.com");

        Info information = new Info()
                .title("Spanish Weather API")
                .version("1.0")
                .description("This API exposes endpoints to get weather info in spanish cities")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}