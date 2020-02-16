package com.amirightornot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DemoBadRequestException extends RuntimeException {
  private static final long serialVersionUID = 960175545919446319L;
  
  private final int httpStatusCode;
  private final int statusCode = 401;

  public DemoBadRequestException() {
      this.httpStatusCode = statusCode;
  }
  
  public DemoBadRequestException(String message) {
      super(message);
      this.httpStatusCode = statusCode;
  }

  public DemoBadRequestException(String message, Throwable cause) {
      super(message, cause);
      this.httpStatusCode = statusCode;
  }

  public DemoBadRequestException(Throwable cause) {
      super(cause);
      this.httpStatusCode = statusCode;
  }

  public DemoBadRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
      super(message, cause, enableSuppression, writableStackTrace);
      this.httpStatusCode = statusCode;
  }

  public int getHttpStatusCode() {
      return httpStatusCode;
  }
}
