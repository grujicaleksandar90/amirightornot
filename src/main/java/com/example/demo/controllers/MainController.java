package com.example.demo.controllers;

import javax.ws.rs.BadRequestException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.auth.AuthUtil;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
public class MainController {

  private final static String ACCESS_TOKEN = "access_token";

  @Autowired
  private UserService userService;

  @GetMapping("/hello")
  public String getHello() {
    return "Hello";
  }

  @PostMapping(value = "/authorization", produces = "application/json")
  public String auth(@RequestBody User user) {
    if (user.getUsername() != null && user.getPassword() != null) {
      AuthUtil.validateUserCredentials(user, userService.getUsers());
      return new JSONObject().put(ACCESS_TOKEN, AuthUtil.createToken(user)).toString();
    } else {
      throw new BadRequestException("Credentails are missing!");
    }
  }
}
