package com.amirightornot.utils;

import javax.ws.rs.BadRequestException;
import com.amirightornot.model.User;

public class Util {

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
}
