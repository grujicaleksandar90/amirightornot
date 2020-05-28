package com.amirightornot.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.amirightornot.cofig.DatabaseConfig;
import com.amirightornot.model.Post;
import com.amirightornot.repositories.PostRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class PostRepositoryImpl implements PostRepository {

  private static final String HEADER_API_KEY = "x-apiKey";

  private RestTemplate restTemplate;
  private DatabaseConfig dbConfig;

  @Autowired
  public PostRepositoryImpl(final RestTemplate restTemplate, final DatabaseConfig dbConfig) {
    this.restTemplate = restTemplate;
    this.dbConfig = dbConfig;
  }

  @Override
  public Post createPost(Post post) {
    HttpEntity<Post> entity = new HttpEntity<>(post, buildHeaders());
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(dbConfig.getPostsEndpoint());
    log.debug("Sending POST request to restdb url: {}", builder.toUriString());
    return restTemplate
        .exchange(builder.build(false).toUriString(), HttpMethod.POST, entity, Post.class)
        .getBody();
  }

  private HttpHeaders buildHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set(HEADER_API_KEY, dbConfig.getApiKey());
    headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    return headers;
  }
}
