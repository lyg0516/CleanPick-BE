package com.kdev5.cleanpick.manager.infra.querydsl;

import com.kdev5.cleanpick.cleaning.domain.QCleaning;
import com.kdev5.cleanpick.manager.domain.QManagerAvailableCleaning;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ManagerAvailableCleaningRepositoryImpl implements ManagerAvailableCleaningRepositoryCustom {

    private final JPAQueryFactory queryFactory;

}
