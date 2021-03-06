package com.amirightornot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AironBadRequestException extends RuntimeException {
  private static final long serialVersionUID = 960175545919446319L;

  public AironBadRequestException(String message) {
    super(message);
  }
}
