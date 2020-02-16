package com.amirightornot.cofig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration("dbConfig")
@ConfigurationProperties("restdb.config")
public class DatabaseConfig {

  private String apiKey;

  private String usersEndpoint;
}
