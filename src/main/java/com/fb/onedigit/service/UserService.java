package com.fb.onedigit.service;

import com.fb.onedigit.models.User;
import com.fb.onedigit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserService extends BaseCrudService<User, Long, UserRepository> {

    @Override
    protected String getEntityName() {
        return User.class.getSimpleName();
    }

    @Override
    protected Optional<User> findEntityByProperty(User user) {
        return this.repository.findByUidAndRemovedAtIsNull(user.getUid());
    }
}
