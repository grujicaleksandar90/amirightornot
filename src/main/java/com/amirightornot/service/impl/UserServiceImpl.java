package com.amirightornot.service.impl;

import java.util.List;
import javax.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.amirightornot.model.User;
import com.amirightornot.repositories.DbRepository;
import com.amirightornot.service.UserService;

@Component
public class UserServiceImpl implements UserService {

  private final DbRepository dbRepository;

  @Autowired
  public UserServiceImpl(DbRepository dbRepository) {
    this.dbRepository = dbRepository;
  }

  public List<User> getUser(User user) {
    return dbRepository.getUser(user);
  }

  public User createUser(User user) {
    return dbRepository.createUser(user);
  }

  public void validateUsername(User user) {
    if (!dbRepository.getUser(user).isEmpty()) {
      throw new BadRequestException("Username already exists!");
    }
  }
}
