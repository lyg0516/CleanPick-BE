package com.kdev5.cleanpick.payment.Infra.external.toss;

import java.util.Base64;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.kdev5.cleanpick.payment.service.PaymentClient;
import com.kdev5.cleanpick.payment.service.dto.api.BillingApiResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TossClient implements PaymentClient {

	@Value("${PSP.toss.secretKey}")
	private String secretKey;

	private static final String BILLING_URL = "https://api.tosspayments.com/v1/billing/authorizations/issue";

	private final RestTemplate restTemplate;

	public BillingApiResponse issueBillingKey(String customerKey, String authKey) {
		String credentials = Base64.getEncoder().encodeToString((secretKey + ":").getBytes());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Basic " + credentials);

		Map<String, String> body = Map.of(
			"customerKey", customerKey,
			"authKey", authKey
		);

		HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);

		try {
			return Objects.requireNonNull(restTemplate.exchange(
				BILLING_URL,
				HttpMethod.POST,
				request,
				TossBillingApiResponse.class
			).getBody()).toBillingApiResponse();
		} catch (HttpClientErrorException e) {
			throw new TossApiException("Toss Billing API 호출 실패: " + e.getResponseBodyAsString());
		}
	}
}
