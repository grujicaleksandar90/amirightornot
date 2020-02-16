package com.amirightornot.auth;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BadRequestException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.amirightornot.exceptions.DemoUnauthorizedException;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

  private static final String AUTHORIZATION_HEADER = "Authorization";
  private static final String LOGIN_PATH = "login";
  private static final String REGISTER_PATH = "register";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    if (request.getHeader(AUTHORIZATION_HEADER) != null) {
      try {
        AuthUtil.validateToken(request.getHeader(AUTHORIZATION_HEADER));
      } catch (BadRequestException | DemoUnauthorizedException e) {
        resolveException(response, e);
        return;
      }
    } else {
      if (!request.getRequestURL().toString().contains(LOGIN_PATH)
          && !request.getRequestURL().toString().contains(REGISTER_PATH)) {
        AuthUtil.buildHttpServletResponse(response, HttpServletResponse.SC_FORBIDDEN,
            "Authorization header is not present.");
        return;
      }
    }
    filterChain.doFilter(request, response);
  }

  private void resolveException(HttpServletResponse response, Exception e) throws IOException {
    if (e.getClass().equals(DemoUnauthorizedException.class)) {
      AuthUtil.buildHttpServletResponse(response, HttpServletResponse.SC_UNAUTHORIZED,
          e.getMessage());
    } else {
      AuthUtil.buildHttpServletResponse(response, HttpServletResponse.SC_BAD_REQUEST,
          e.getMessage());
    }
  }
}
