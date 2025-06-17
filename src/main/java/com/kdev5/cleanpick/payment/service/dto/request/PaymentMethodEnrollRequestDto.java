package com.kdev5.cleanpick.payment.service.dto.request;

import lombok.Getter;

@Getter
public class PaymentMethodEnrollRequestDto {

	private String authKey;

	private String customerKey;
}
