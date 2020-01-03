package com.example.demo.auth;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.ws.rs.BadRequestException;
import com.example.demo.model.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;

public class AuthUtil {

  private static final String TOKEN_PREFIX = "Bearer ";
  private static final String ISSUER = "demo.inc";

  public static void validateUserCredentials(User user, List<User> dbUsers) {
    Optional<User> dbUser = dbUsers.stream().filter(u -> u.getUsername().equals(user.getUsername())
        && u.getPassword().equals(user.getPassword())).findFirst();
    if (!dbUser.isPresent()) {
      throw new BadRequestException("Invalid credentials!");
    }
  }

  public static void validateToken(String token) {
    if (!token.startsWith(TOKEN_PREFIX)) {
      throw new BadRequestException("Authorization token must start with Bearer.");
    } else {
      try {
        TokenHelper.parseJWT(token.substring(7));
      } catch (JwtException e) {
        throw new BadRequestException(
            e instanceof ExpiredJwtException ? "Authorization token has expired."
                : "Authorization token is invalid");
      }
    }
  }

  public static String createToken(User user) {
    return TokenHelper.createJWT(UUID.randomUUID().toString(), ISSUER, user.getUsername());
  }
}
