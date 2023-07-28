package br.com.techchallenge.energymonitor.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {
    @Value("${spring.openapi.url}")
    private String prodUrl;

    @Bean
    public OpenAPI setup() {
        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL em ambiente de prod");

        Contact contact = new Contact();
        contact.setEmail("teste@teste.com");
        contact.setName("FIAP");
        contact.setUrl("https://www.fiap.com.br");

        Info info = new Info()
                .title("Tutorial Management API")
                .version("1.0")
                .contact(contact)
                .description("Essa API fornece endpoints para gerenciamento de pessoas, endereços e eletrônicos.");

        return new OpenAPI().info(info).servers(List.of(prodServer));
    }



}
