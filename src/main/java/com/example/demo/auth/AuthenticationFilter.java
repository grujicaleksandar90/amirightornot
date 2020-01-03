package com.example.demo.auth;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BadRequestException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.demo.exceptions.DemoUnauthorizedException;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

  private static final String AUTHORIZATION_HEADER = "Authorization";
  private static final String AUTH_PATH = "authorization";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    if (request.getHeader(AUTHORIZATION_HEADER) != null) {
      try {
        AuthUtil.validateToken(request.getHeader(AUTHORIZATION_HEADER));
      } catch(BadRequestException e) {
        throw new DemoUnauthorizedException(e.getMessage());
      }
    } else {
      if (!request.getRequestURL().toString().contains(AUTH_PATH)) {
        throw new DemoUnauthorizedException("Authorization header is not present.");
      }
    }
    filterChain.doFilter(request, response);
  }
}