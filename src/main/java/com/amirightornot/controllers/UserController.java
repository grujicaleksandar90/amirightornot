package com.amirightornot.controllers;

import javax.ws.rs.BadRequestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.amirightornot.auth.AuthUtil;
import com.amirightornot.exceptions.DemoBadRequestException;
import com.amirightornot.exceptions.DemoUnauthorizedException;
import com.amirightornot.model.User;
import com.amirightornot.service.UserService;
import com.amirightornot.utils.Util;

@RestController
public class UserController {

  private final static String ACCESS_TOKEN = "access_token";

  @Autowired
  private UserService userService;

  @GetMapping("/hello")
  public String getHello() {
    return "Hello";
  }

  @PostMapping(value = "/login", produces = "application/json")
  public String login(@RequestBody User user) {
    try {
      if ((user.getUsername() != null && user.getPassword() != null)
          && !userService.getUser(user).isEmpty()) {
        return new JSONObject().put(ACCESS_TOKEN, AuthUtil.createToken(user)).toString();
      } else {
        throw new DemoUnauthorizedException("Credentails are missing!");
      }
    } catch (BadRequestException e) {
      throw new DemoBadRequestException(e.getMessage());
    }
  }

  @PostMapping(value = "/register", produces = "application/json")
  public User register(@RequestBody User user) {
    try {
      Util.validateUser(user);
      return userService.createUser(user);
    } catch (BadRequestException e) {
      throw new DemoBadRequestException(e.getMessage());
    }
  }
}
