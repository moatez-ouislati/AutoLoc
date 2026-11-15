package tn.esprit.autoloc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
/*
@OpenAPIDefinition(
        info = @Info(
                title = "AutoLoc API",
                version = "1.0.0",
                description = "REST API for vehicle rental management",
                contact = @Contact(
                        name = "AutoLoc Development Team",
                        email = "moataz@gmail.com",
                        url = "https://autoloc.com"

                )
        )

)
*/

public class AutolocApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutolocApiApplication.class, args);
    }

}
