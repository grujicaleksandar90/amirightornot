package com.amirightornot.service;

import com.amirightornot.model.Post;

public interface PostService {

  Post createPost(Post post, String token);
}
