package ru.dinerik.ECM.swagger.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Электронная система документооборота",
                description = "ECM System", version = "1.0.0",
                contact = @Contact(
                        name = "Erik",
                        email = "ukolists@gmail.com",
                        url = "https://dinerik.ru"
                )
        )
)
public class OpenApiConfig {

}