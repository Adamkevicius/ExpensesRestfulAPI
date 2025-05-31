package lt.viko.eif.madamkevicius.expansesapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Expenses API")
                        .version("1.0")
                        .description("This API allows you to manage personal expenses including tracking, updating, and deleting expense entries.")
                );
    }
}
