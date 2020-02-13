package com.example.demo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.demo.model.User;
import com.example.demo.repositories.DbRepository;
import com.example.demo.service.UserService;

@Component
public class UserServiceImpl implements UserService {

  private final DbRepository dbRepository;

  @Autowired
  public UserServiceImpl(DbRepository dbRepository) {
    this.dbRepository = dbRepository;
  }

  @Override
  public List<User> getUsers() {
    return dbRepository.getAllUsers();
  }
}
