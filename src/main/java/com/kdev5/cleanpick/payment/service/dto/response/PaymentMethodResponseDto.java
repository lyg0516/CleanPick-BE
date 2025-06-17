package com.kdev5.cleanpick.payment.service.dto.response;

import com.kdev5.cleanpick.payment.domain.PaymentMethod;

import lombok.Getter;

@Getter
public class PaymentMethodResponseDto {

	private final String cardCompany;

	private final String cardNumber;

	private final String type;

	private final String cardType;

	private PaymentMethodResponseDto(String cardCompany, String cardNumber, String type, String cardType) {
		this.cardCompany = cardCompany;
		this.cardNumber = cardNumber;
		this.type = type;
		this.cardType = cardType;
	}

	public static PaymentMethodResponseDto fromEntity(PaymentMethod paymentMethod) {
		return new PaymentMethodResponseDto(
			paymentMethod.getCardCompany(),
			paymentMethod.getCardNumber(),
			paymentMethod.getType().toString(),
			paymentMethod.getCardType().getValue()
		);
	}
}
