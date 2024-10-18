package dev.nhairlahovic.productservice.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.IOException;

@Slf4j
@Configuration
public class DataSourceConfig {

    @Value("${service-offering.name}")
    private String serviceOfferingName;

    @Bean
    public DataSource dataSource() {
        String vcapServices = System.getenv("VCAP_SERVICES");

        if (vcapServices == null || vcapServices.isEmpty()) {
            log.info("VCAP_SERVICES not found");
            throw new RuntimeException("VCAP_SERVICES not found");
        }

        try {
            log.info("VCAP_SERVICES found: {}", vcapServices);

            // Parse the VCAP_SERVICES environment variable to extract the credentials
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(vcapServices);
            JsonNode credentials = root.path(serviceOfferingName).get(0).path("credentials");

            String url = credentials.get("uri").asText();
            String username = credentials.get("user").asText();
            String password = credentials.get("password").asText();

            String jdbcUrl = getJdbcUrl(url);

            // Return a DataSource configured with the extracted credentials
            return postgresDataSource(jdbcUrl, username, password);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse VCAP_SERVICES", e);
        }
    }

    private String getJdbcUrl(String url) {
        String uri = url.substring("postgres://".length());
        uri = uri.replace("sslmode=verify-full", "sslmode=disable");

        // Extract user and password
        String[] userPassAndHost = uri.split("@");

        // Extract host part and construct JDBC URL including the query parameters
        String[] hostAndParams = userPassAndHost[1].split("\\?", 2);
        String hostAndPath = hostAndParams[0];
        String queryParams = hostAndParams.length > 1 ? "?" + hostAndParams[1] : "";

        return "jdbc:postgresql://" + hostAndPath + queryParams;
    }

    private DataSource postgresDataSource(String url, String username, String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

}
