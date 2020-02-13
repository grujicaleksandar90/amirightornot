package com.example.demo.repositories;

import java.util.List;
import com.example.demo.model.User;

public interface DbRepository {

  List<User> getAllUsers();

}
