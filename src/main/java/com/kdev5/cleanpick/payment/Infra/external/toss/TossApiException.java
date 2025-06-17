package com.kdev5.cleanpick.payment.Infra.external.toss;

import com.kdev5.cleanpick.global.exception.BaseException;

public class TossApiException extends RuntimeException {
  public TossApiException(String message) {
    super(message);
  }
}
