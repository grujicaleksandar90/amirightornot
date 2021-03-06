package com.amirightornot.controllers;

import javax.ws.rs.BadRequestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.amirightornot.auth.AuthUtil;
import com.amirightornot.exceptions.AironBadRequestException;
import com.amirightornot.model.User;
import com.amirightornot.service.UserService;
import com.amirightornot.utils.Util;

@RestController
public class UserController {

  private static final String ACCESS_TOKEN = "access_token";

  @Autowired
  private UserService userService;

  @GetMapping("/hello")
  public String getHello() {
    return "Hello";
  }

  @PostMapping(value = "/login", produces = "application/json")
  public String login(@RequestBody User user) {
    if ((user.getUsername() != null && user.getPassword() != null)
        && !userService.getUser(user).isEmpty()) {
      return new JSONObject().put(ACCESS_TOKEN, AuthUtil.createToken(user)).toString();
    } else {
      throw new AironBadRequestException("Invalid credentials.");
    }
  }

  @PostMapping(value = "/register", produces = "application/json")
  public User register(@RequestBody User user) {
    try {
      doValidation(user);
      return userService.createUser(user);
    } catch (BadRequestException e) {
      throw new AironBadRequestException(e.getMessage());
    }
  }

  private void doValidation(User user) {
    Util.validateUser(user);
    userService.validateUsername(user);
  }
}
