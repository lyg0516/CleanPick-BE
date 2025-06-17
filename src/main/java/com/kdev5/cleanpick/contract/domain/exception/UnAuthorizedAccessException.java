package com.kdev5.cleanpick.contract.domain.exception;

import com.kdev5.cleanpick.global.exception.BaseException;
import com.kdev5.cleanpick.global.exception.ErrorCode;

public class UnAuthorizedAccessException extends BaseException {
	public UnAuthorizedAccessException(ErrorCode errorCode) {
		super(errorCode);
	}
}
