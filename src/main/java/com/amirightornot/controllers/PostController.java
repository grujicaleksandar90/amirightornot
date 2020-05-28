package com.amirightornot.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.amirightornot.model.Post;
import com.amirightornot.service.PostService;
import com.amirightornot.utils.Util;

@RestController
@RequestMapping("/posts")
public class PostController {

  private static final String AUTHORIZATION_HEADER = "Authorization";
  private static final String POST_CREATED_JSON_STRING = "{ status: Post created successfully.}";

  private PostService postService;

  @Autowired
  public PostController(final PostService postService) {
    this.postService = postService;
  }

  @PostMapping(produces = "application/json")
  public ResponseEntity<String> createPost(@RequestBody Post post,
      @RequestHeader(AUTHORIZATION_HEADER) String token) {
    Util.validatePostMessage(post.getMessage());
    postService.createPost(post, token);
    return new ResponseEntity<>(new JSONObject(POST_CREATED_JSON_STRING).toString(),
        HttpStatus.CREATED);
  }
}
