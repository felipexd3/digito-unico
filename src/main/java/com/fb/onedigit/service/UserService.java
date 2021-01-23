package com.fb.onedigit.service;

import com.fb.onedigit.exceptions.ApplicationException;
import com.fb.onedigit.messages.Messages;
import com.fb.onedigit.models.User;
import com.fb.onedigit.models.builders.UserDTOBuilder;
import com.fb.onedigit.models.dtos.UserDTO;
import com.fb.onedigit.repository.UserRepository;
import com.fb.onedigit.service.base.BaseCrudService;
import com.fb.onedigit.util.RSAUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService extends BaseCrudService<User, Long, UserRepository> {

    @Override
    protected String getEntityName() {
        return User.class.getSimpleName();
    }

    @Override
    protected Optional<User> findEntityByProperty(User user) {
        return this.repository.findByUidAndRemovedAtIsNull(user.getUid());
    }

    public UserDTO cryptUser(UUID userUid, String publicKey) {
        var user = this.findByUid(userUid)
            .orElseThrow(() -> new ApplicationException(Messages.ENTITY_NOT_FOUND, HttpStatus.NO_CONTENT));
        var publicKeyDecoded = RSAUtil.decodePublicKey(publicKey);
        user.setName(RSAUtil.crypter(user.getName(), publicKeyDecoded));
        user.setEmail(RSAUtil.crypter(user.getEmail(), publicKeyDecoded));
        return UserDTOBuilder.fromEntity(user);
    }
}
