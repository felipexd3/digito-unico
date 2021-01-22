package com.fb.onedigit.repository;

import com.fb.onedigit.models.OneDigit;
import com.fb.onedigit.repository.base.BaseCrudRepository;

import java.util.List;
import java.util.UUID;

public interface OneDigitRepository extends BaseCrudRepository<OneDigit, Long> {
    List<OneDigit> findAllByUser_Uid(UUID userUid);
}
