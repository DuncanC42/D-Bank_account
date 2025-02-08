package bzh.duncan.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts microservice REST API Documentation",
                version = "1.0",
                description = "Our Bank Accounts microservice REST API documentation",
                contact = @Contact(
                        name = "Duncan",
                        email = "cadoret.du@gmail.com",
                        url = "https://duncan-cadoret.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Accounts microservice Wiki Documentation",
                url = ""
        )
)
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
