package com.amirightornot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class DemoUnauthorizedException extends RuntimeException {
  private static final long serialVersionUID = 960175545919446319L;

  public DemoUnauthorizedException(String message) {
    super(message);
  }
}
