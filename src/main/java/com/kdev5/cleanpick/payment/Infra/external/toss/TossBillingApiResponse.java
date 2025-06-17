package com.kdev5.cleanpick.payment.Infra.external.toss;

import com.kdev5.cleanpick.payment.service.dto.api.BillingApiResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TossBillingApiResponse{
	private String billingKey;
	private String customerKey;
	private String method;
	private Card card;
	private String cardCompany;

	@Getter
	@NoArgsConstructor
	public static class Card {
		private String company;
		private String number;
		private String ownerType;
		private String cardType;
	}

	public BillingApiResponse toBillingApiResponse() {
		return BillingApiResponse.builder()
			.billingKey(billingKey)
			.customerKey(customerKey)
			.method(method)
			.cardCompany(cardCompany)
			.cardNumber(card.number)
			.ownerType(card.ownerType)
			.cardType(card.cardType)
			.build();
	}

}
