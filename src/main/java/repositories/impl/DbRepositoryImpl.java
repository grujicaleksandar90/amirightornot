package repositories.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import repositories.DbRepository;

@Repository
@Slf4j
public class DbRepositoryImpl implements DbRepository {

  private RestTemplate restClient;

  @Autowired
  public DbRepositoryImpl(RestTemplate restClient) {
    this.restClient = restClient;
  }

  @Override
  public ArrayList<User> getAllUsers() {
    HttpEntity<String> entity = new HttpEntity<>(buildHeaders());
    UriComponentsBuilder builder =
        UriComponentsBuilder.fromHttpUrl("https://demoapp-32d9.restdb.io/rest/demousers");
    log.debug("Sending GET request to restdb url: {}", builder.toUriString());
    ArrayList<User> users = restClient.exchange(builder.build(false).toUriString(), HttpMethod.GET,
        entity, new ParameterizedTypeReference<ArrayList<User>>() {}).getBody();
    return users;
  }

  private HttpHeaders buildHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("x-apiKey", "3b907f848eae798e17c7b25018187e5023e7d");
    headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    return headers;
  }
}
