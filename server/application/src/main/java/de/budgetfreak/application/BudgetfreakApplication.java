package de.budgetfreak.application;

import de.budgetfreak.usermanagement.UserManagementConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@Import(UserManagementConfiguration.class)
public class BudgetfreakApplication {

    public static void main(String[] args) {
        SpringApplication.run(BudgetfreakApplication.class, args);
    }

    //TODO change that to custom mapping (not globaly!)
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**");
            }
        };
    }
}
