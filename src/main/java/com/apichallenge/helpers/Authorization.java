package com.apichallenge.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Authorization {
  private static final String authKey = "21fd2eb403794394ef66db42d12983d2";

  public static void isValid(String authKey) {
    if (!Authorization.authKey.equals(authKey)) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized");
    }
  }
}
