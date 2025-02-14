package dev.nhairlahovic.productservice.config;

import org.springframework.boot.autoconfigure.flyway.FlywayConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class FlywayConfig {

    @Bean
    public FlywayConfigurationCustomizer commonFlywayConfig() {
        return configuration -> configuration.locations("classpath:db/migration");
    }

    @Bean
    @Profile({"seed", "test"})
    public FlywayConfigurationCustomizer flywaySeedConfig() {
        return configuration -> configuration.locations("classpath:db/migration", "classpath:db/seed");
    }
}
