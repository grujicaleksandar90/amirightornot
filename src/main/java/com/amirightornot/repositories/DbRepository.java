package com.amirightornot.repositories;

import java.util.List;
import com.amirightornot.model.User;

public interface DbRepository {

  List<User> getUser(User user);

  User createUser(User user);
}
