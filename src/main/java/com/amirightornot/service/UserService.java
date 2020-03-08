package com.amirightornot.service;

import java.util.List;
import com.amirightornot.model.User;

public interface UserService {

  List<User> getUser(User user);

  User createUser(User user);
  
  void validateUsername(User user);
}
