package com.kdev5.cleanpick.payment.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdev5.cleanpick.payment.Infra.PaymentMethodRepository;
import com.kdev5.cleanpick.payment.service.dto.api.BillingApiResponse;
import com.kdev5.cleanpick.payment.Infra.external.toss.TossClient;
import com.kdev5.cleanpick.payment.service.dto.request.PaymentMethodEnrollRequestDto;
import com.kdev5.cleanpick.payment.service.dto.response.PaymentMethodResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentMethodService {

	private final PaymentMethodRepository paymentMethodRepository;
	private final PaymentClient paymentClient;

	@Transactional
	public void paymentMethodEnroll(Long customerId, PaymentMethodEnrollRequestDto paymentMethodEnrollRequestDto) {

		BillingApiResponse response = paymentClient.issueBillingKey(
			paymentMethodEnrollRequestDto.getCustomerKey(),
			paymentMethodEnrollRequestDto.getAuthKey()
		);

		paymentMethodRepository.save(response.toEntity(customerId));
	}

	public List<PaymentMethodResponseDto> getPaymentMethods(Long customerId) {
		return paymentMethodRepository.findByCustomerId(customerId)
			.stream().map(PaymentMethodResponseDto::fromEntity).toList();
	}
}
