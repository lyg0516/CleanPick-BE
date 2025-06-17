package com.kdev5.cleanpick.contract.service.validator;

import org.springframework.stereotype.Component;

import com.kdev5.cleanpick.contract.domain.Contract;
import com.kdev5.cleanpick.contract.domain.exception.ContractException;
import com.kdev5.cleanpick.contract.domain.exception.UnAuthorizedAccessException;
import com.kdev5.cleanpick.contract.infra.ContractRepository;
import com.kdev5.cleanpick.global.exception.ErrorCode;
import com.kdev5.cleanpick.user.domain.Role;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ContractAccessValidator {

	private final ContractRepository contractRepository;

	public void validateReadableBy(Long contractId, Long userId, Role role) {
		Contract contract = contractRepository.findById(contractId)
			.orElseThrow(() -> new ContractException(ErrorCode.CONTRACT_NOT_FOUND));

		boolean isCustomer = role == Role.CUSTOMER && contract.getCustomer().getId().equals(userId);
		boolean isManager = role == Role.MANAGER && contract.getManager() != null && contract.getManager().getId().equals(userId);

		if (!isCustomer && !isManager) {
			throw new UnAuthorizedAccessException(ErrorCode.ACCESS_FORBIDDEN);
		}
	}
}
