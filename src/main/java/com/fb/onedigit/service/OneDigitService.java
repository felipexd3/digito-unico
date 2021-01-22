package com.fb.onedigit.service;

import com.fb.onedigit.exceptions.ApplicationException;
import com.fb.onedigit.messages.Messages;
import com.fb.onedigit.models.OneDigit;
import com.fb.onedigit.models.builders.OneDigitDTOBuilder;
import com.fb.onedigit.models.dtos.OneDigitDTO;
import com.fb.onedigit.repository.OneDigitRepository;
import com.fb.onedigit.service.base.BaseCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class OneDigitService extends BaseCrudService<OneDigit, Long, OneDigitRepository> {
    private final UserService userService;

    @Override
    protected String getEntityName() {
        return null;
    }

    @Override
    protected Optional<OneDigit> findEntityByProperty(OneDigit entity) {
        return Optional.empty();
    }

    public Integer processNumber(OneDigitDTO oneDigitDTO) {
        var digit = this.findOneDigit(oneDigitDTO.getNumber().repeat(oneDigitDTO.getExp()));
        var user = Objects.nonNull(oneDigitDTO.getUserUid()) ?
            this.userService.findByUid(oneDigitDTO.getUserUid())
                .orElseThrow(() ->
                    new ApplicationException(String.format(Messages.ENTITY_NOT_FOUND, this.getEntityName()))) :
            null;
        this.repository.save(OneDigitDTOBuilder.toEntity(oneDigitDTO, digit, user));
        return digit;
    }

    public Integer findOneDigit(String numbers) {
        var digit = numbers.chars().map(Character::getNumericValue).sum();
        return digit > 9 ? this.findOneDigit(String.valueOf(digit)) : digit;
    }

    public List<OneDigit> findAllByUser(UUID userUid) {
        return this.repository.findAllByUser_Uid(userUid);
    }
}
