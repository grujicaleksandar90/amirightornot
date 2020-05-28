package com.amirightornot.utils;

import javax.ws.rs.BadRequestException;
import org.springframework.util.StringUtils;
import com.amirightornot.exceptions.AironBadRequestException;
import com.amirightornot.model.User;

public class Util {

  private Util() {}

  public static void validateUser(User user) {
    String missingField = null;

    if (user.getName() == null) {
      missingField = "Name";
    } else if (user.getUsername() == null) {
      missingField = "Username";
    } else if (user.getPassword() == null) {
      missingField = "Password";
    }

    if (missingField != null) {
      throw new BadRequestException(missingField.concat(" must be populated."));
    }
  }

  public static void validatePostMessage(String message) {
    if (StringUtils.isEmpty(message) || message.length() < 20) {
      throw new AironBadRequestException("Message is empty or too short.");
    }
  }
}
