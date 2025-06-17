package com.kdev5.cleanpick.payment.domain.enumeration;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CardType {
	DEBIT("체크"), CREDIT("신용");

	private final String value;

	public static CardType fromValue(String value) {
		return Arrays.stream(CardType.values())
			.filter(type -> type.getValue().equals(value))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("Unknown card type: " + value));
	}
}
