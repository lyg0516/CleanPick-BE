package com.kdev5.cleanpick.manager.service;

import com.kdev5.cleanpick.cleaning.domain.Cleaning;
import com.kdev5.cleanpick.global.exception.ErrorCode;
import com.kdev5.cleanpick.manager.domain.Manager;
import com.kdev5.cleanpick.manager.domain.ManagerAvailableCleaning;
import com.kdev5.cleanpick.manager.domain.ManagerAvailableTime;

import com.kdev5.cleanpick.manager.domain.exception.ManagerNotFoundException;
import com.kdev5.cleanpick.manager.infra.repository.ManagerAvailableCleaningRepository;

import com.kdev5.cleanpick.manager.infra.repository.ManagerAvailableTimeRepository;
import com.kdev5.cleanpick.manager.infra.repository.ManagerRepository;
import com.kdev5.cleanpick.manager.service.dto.request.ManagerDetailRequestDto;
import com.kdev5.cleanpick.manager.service.dto.response.ManagerPrivateResponseDto;
import com.kdev5.cleanpick.manager.service.dto.response.ManagerSearchResponseDto;
import com.kdev5.cleanpick.review.Infra.ReviewRepository;
import com.kdev5.cleanpick.user.domain.User;
import com.kdev5.cleanpick.user.domain.exception.UserNotFoundException;
import com.kdev5.cleanpick.user.infra.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ManagerService {

    private final UserRepository userRepository;
    private final ManagerRepository managerRepository;
    private final ReviewRepository reviewRepository;
    private final ManagerAvailableCleaningRepository managerAvailableCleaningRepository;
    private final ManagerAvailableTimeRepository managerAvailableTimeRepository;

    @Transactional
    public ManagerPrivateResponseDto enrollManager(Long userId, ManagerDetailRequestDto managerDetailRequestDto) {

        User user = userRepository.findById(userId).orElseThrow(
            () -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND)
        );

        user.activate();

        final Manager manager = managerRepository.save(managerDetailRequestDto.toManagerEntity(user));

        List<ManagerAvailableCleaning> managerAvailableCleanings = managerDetailRequestDto.getAvailableCleans().stream().map(
            cleaningId -> new ManagerAvailableCleaning(manager, Cleaning.reference(cleaningId))
        ).toList();

        managerAvailableCleaningRepository.saveAll(managerAvailableCleanings);

        List<ManagerAvailableTime> managerAvailableTimes = managerDetailRequestDto.getAvailableTimes().stream().map(
            availableTimeDto -> availableTimeDto.toManagerAvailableTime(manager)
        ).toList();

        managerAvailableTimeRepository.saveAll(managerAvailableTimes);

        return ManagerPrivateResponseDto.fromEntity(manager);
    }

    public ManagerPrivateResponseDto getManager(Long managerId) {
        final Manager manager = managerRepository.findById(managerId).orElseThrow(
            () -> new ManagerNotFoundException(ErrorCode.MANAGER_NOT_FOUND)
        );
        return ManagerPrivateResponseDto.fromEntity(manager);
    }

}

