package com.amirightornot.repositories.impl;

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
import com.amirightornot.cofig.DatabaseConfig;
import com.amirightornot.model.User;
import com.amirightornot.repositories.DbRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DbRepositoryImpl implements DbRepository {

  private static final String HEADER_API_KEY = "x-apiKey";
  private static final String QUERY_PARAM_NAME = "q";
  private static final String QUERY_PARAM_VALUE = "{\"username\": \"%s\", \"password\": \"%s\"}";

  private RestTemplate restClient;
  private DatabaseConfig dbConfig;

  @Autowired
  public DbRepositoryImpl(RestTemplate restClient, DatabaseConfig dbConfig) {
    this.restClient = restClient;
    this.dbConfig = dbConfig;
  }

  @Override
  public ArrayList<User> getUser(User user) {
    String queryPath = String.format(QUERY_PARAM_VALUE, user.getUsername(), user.getPassword());
    HttpEntity<String> entity = new HttpEntity<>(buildHeaders());
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(dbConfig.getUsersEndpoint())
        .queryParam(QUERY_PARAM_NAME, queryPath);
    return restClient.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity,
        new ParameterizedTypeReference<ArrayList<User>>() {}).getBody();
  }

  @Override
  public User createUser(User user) {
    user.setId("1234"); // This should be UUID.randomUUID()
    HttpEntity<User> entity = new HttpEntity<>(user, buildHeaders());
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(dbConfig.getUsersEndpoint());
    log.debug("Sending POST request to restdb url: {}", builder.toUriString());
    return restClient
        .exchange(builder.build(false).toUriString(), HttpMethod.POST, entity, User.class)
        .getBody();
  }

  private HttpHeaders buildHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HEADER_API_KEY, dbConfig.getApiKey());
    headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    return headers;
  }
}
