package com.fb.onedigit.service;

import com.fb.onedigit.exceptions.ApplicationException;
import com.fb.onedigit.messages.Messages;
import com.fb.onedigit.models.OneDigit;
import com.fb.onedigit.models.User;
import com.fb.onedigit.models.builders.OneDigitDTOBuilder;
import com.fb.onedigit.models.dtos.OneDigitDTO;
import com.fb.onedigit.repository.OneDigitRepository;
import com.fb.onedigit.service.base.BaseCrudService;
import com.fb.onedigit.util.CacheUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class OneDigitService extends BaseCrudService<OneDigit, Long, OneDigitRepository> {
    private final UserService userService;
    private final CacheUtil cacheUtil;

    @Override
    protected String getEntityName() {
        return OneDigit.class.getSimpleName();
    }

    @Override
    protected Optional<OneDigit> findEntityByProperty(OneDigit entity) {
        return Optional.empty();
    }

    public Integer processNumber(OneDigitDTO oneDigitDTO) {
        var number = oneDigitDTO.getNumber().repeat(oneDigitDTO.getExp());
        var digit = cacheUtil.getCache().containsKey(number) ? cacheUtil.getCache().get(number) : this.findOneDigit(number);
        var user = Objects.nonNull(oneDigitDTO.getUserUid()) ?
            this.userService.findByUid(oneDigitDTO.getUserUid())
                .orElseThrow(() ->
                    new ApplicationException(String.format(Messages.ENTITY_NOT_FOUND, User.class.getSimpleName()))) :
            null;

        this.repository.save(OneDigitDTOBuilder.toEntity(oneDigitDTO, digit, user));
        cacheUtil.getCache().put(number, digit);
        return digit;
    }

    public Integer findOneDigit(String numbers) {
        var digit = Stream.of(numbers.split("")).mapToInt(Integer::parseInt).sum();
        return digit > 9 ? this.findOneDigit(String.valueOf(digit)) : digit;
    }

    public List<OneDigit> findAllByUser(UUID userUid) {
        return this.repository.findAllByUser_Uid(userUid);
    }
}
