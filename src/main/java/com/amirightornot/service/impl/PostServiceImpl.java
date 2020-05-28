package com.amirightornot.service.impl;

import java.time.LocalDate;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.amirightornot.auth.TokenHelper;
import com.amirightornot.model.Post;
import com.amirightornot.repositories.PostRepository;
import com.amirightornot.service.PostService;

@Service
public class PostServiceImpl implements PostService {

  private static final String NUMBER_OF_DAYS_DEFAULT = "7";

  private PostRepository postRepository;

  @Autowired
  public PostServiceImpl(final PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  public Post createPost(Post post, String token) {
    post.setId(UUID.randomUUID().toString());
    post.setDatePosted(LocalDate.now());
    post.setPostedBy(TokenHelper.getUsernameFromToken(token.substring(7)));
    post.setPostedBy(StringUtils.isEmpty(post.getNumberOfDays()) ? NUMBER_OF_DAYS_DEFAULT
        : post.getNumberOfDays());
    return postRepository.createPost(post);
  }
}
