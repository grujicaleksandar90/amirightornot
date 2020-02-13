package com.example.demo.repositories.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.demo.cofig.DatabaseConfig;
import com.example.demo.model.User;
import com.example.demo.repositories.DbRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DbRepositoryImpl implements DbRepository {

  private static final String HEADER_API_KEY = "x-apiKey";

  private RestTemplate restClient;
  private DatabaseConfig dbConfig;

  @Autowired
  public DbRepositoryImpl(RestTemplate restClient, DatabaseConfig dbConfig) {
    this.restClient = restClient;
    this.dbConfig = dbConfig;
  }

  @Override
  public ArrayList<User> getAllUsers() {
    HttpEntity<String> entity = new HttpEntity<>(buildHeaders());
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(dbConfig.getUsersEndpoint());
    log.debug("Sending GET request to restdb url: {}", builder.toUriString());
    ArrayList<User> users = restClient.exchange(builder.build(false).toUriString(), HttpMethod.GET,
        entity, new ParameterizedTypeReference<ArrayList<User>>() {}).getBody();
    return users;
  }

  private HttpHeaders buildHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HEADER_API_KEY, dbConfig.getApiKey());
    headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    return headers;
  }
}
