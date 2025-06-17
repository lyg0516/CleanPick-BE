package com.kdev5.cleanpick.payment.service.dto.api;

import com.kdev5.cleanpick.customer.domain.Customer;
import com.kdev5.cleanpick.payment.domain.PaymentMethod;
import com.kdev5.cleanpick.payment.domain.enumeration.CardType;
import com.kdev5.cleanpick.payment.domain.enumeration.PayType;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BillingApiResponse {

	private final String billingKey;
	private final String customerKey;
	private final String method;
	private final String cardCompany;
	private final String cardNumber;
	private final String ownerType;
	private final String cardType;

	@Builder
	public BillingApiResponse(String billingKey, String customerKey, String method, String cardCompany, String company,
		String cardNumber, String ownerType, String cardType) {
		this.billingKey = billingKey;
		this.customerKey = customerKey;
		this.method = method;
		this.cardCompany = cardCompany;
		this.cardNumber = cardNumber;
		this.ownerType = ownerType;
		this.cardType = cardType;
	}

	public PaymentMethod toEntity(Long customerId) {
		return PaymentMethod.builder()
			.customer(Customer.reference(customerId))
			.type(PayType.card)
			.cardCompany(cardCompany)
			.cardNumber(cardNumber)
			.billingKey(billingKey)
			.cardType(CardType.fromValue(cardType))
			.build();
	}
}
