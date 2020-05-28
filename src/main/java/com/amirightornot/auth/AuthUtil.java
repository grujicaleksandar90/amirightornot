package com.amirightornot.auth;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BadRequestException;
import com.amirightornot.exceptions.AironUnauthorizedException;
import com.amirightornot.model.User;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;

public class AuthUtil {

  private AuthUtil() {}

  private static final String TOKEN_PREFIX = "Bearer ";
  private static final String ISSUER = "demo.inc";

  public static void validateToken(String token) {
    if (!token.startsWith(TOKEN_PREFIX)) {
      throw new BadRequestException("Authorization token must start with Bearer.");
    } else {
      try {
        TokenHelper.parseJWT(token.substring(7));
      } catch (ExpiredJwtException exe) {
        throw new AironUnauthorizedException("Authorization token has expired.");
      } catch (JwtException e) {
        throw new AironUnauthorizedException("Authorization token is invalid");
      }
    }
  }

  public static String createToken(User user) {
    return TokenHelper.createJWT(ISSUER, user.getUsername());
  }

  public static void buildHttpServletResponse(HttpServletResponse response, int status,
      String message) throws IOException {
    response.setStatus(status);
    response.sendError(status, message);
  }
}
