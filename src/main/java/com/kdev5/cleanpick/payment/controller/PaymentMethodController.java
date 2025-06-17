package com.kdev5.cleanpick.payment.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdev5.cleanpick.global.response.ApiResponse;
import com.kdev5.cleanpick.global.security.annotation.CustomerId;
import com.kdev5.cleanpick.payment.service.PaymentMethodService;
import com.kdev5.cleanpick.payment.service.dto.request.PaymentMethodEnrollRequestDto;
import com.kdev5.cleanpick.payment.service.dto.response.PaymentMethodResponseDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payments/method")
@RequiredArgsConstructor
public class PaymentMethodController {

	private final PaymentMethodService paymentService;

	@PostMapping
	public ResponseEntity<ApiResponse<Void>> paymentMethodEnrolled(@CustomerId Long customerId, @RequestBody PaymentMethodEnrollRequestDto paymentMethodEnrollRequestDto) {
		paymentService.paymentMethodEnroll(customerId, paymentMethodEnrollRequestDto);
		return ResponseEntity.ok(ApiResponse.ok());
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<PaymentMethodResponseDto>>> getPaymentMethods(@CustomerId Long customerId) {
		return ResponseEntity.ok(ApiResponse.ok(paymentService.getPaymentMethods(customerId)));
	}


}

