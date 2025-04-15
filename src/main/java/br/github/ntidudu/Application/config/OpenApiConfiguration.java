package br.github.ntidudu.Application.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;


@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "HelPEdu API",
        version = "v1",
        contact = @Contact(
            name = "Eduardo Couto",
            email = "coutoeduardo858@gmail.com"
        )
    )
)
public class OpenApiConfiguration {
    
    


}
