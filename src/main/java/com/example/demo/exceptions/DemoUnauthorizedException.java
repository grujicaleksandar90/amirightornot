package com.example.demo.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus()
public class DemoUnauthorizedException extends RuntimeException {
  private static final long serialVersionUID = 960175545919446319L;
  
  private final int httpStatusCode;
  private final int statusCode = 401;

  public DemoUnauthorizedException() {
      this.httpStatusCode = statusCode;
  }
  
  public DemoUnauthorizedException(String message) {
      super(message);
      this.httpStatusCode = statusCode;
  }

  public DemoUnauthorizedException(String message, Throwable cause) {
      super(message, cause);
      this.httpStatusCode = statusCode;
  }

  public DemoUnauthorizedException(Throwable cause) {
      super(cause);
      this.httpStatusCode = statusCode;
  }

  public DemoUnauthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
      this.httpStatusCode = statusCode;
  }

  public int getHttpStatusCode() {
      return httpStatusCode;
  }
}
