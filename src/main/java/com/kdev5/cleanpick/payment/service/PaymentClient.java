package com.kdev5.cleanpick.payment.service;

import com.kdev5.cleanpick.payment.service.dto.api.BillingApiResponse;

public interface PaymentClient {

	BillingApiResponse issueBillingKey(String customerKey, String authKey);
}
