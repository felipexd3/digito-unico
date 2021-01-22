package com.fb.onedigit.service;

import com.fb.onedigit.models.User;
import com.fb.onedigit.repository.UserRepository;
import com.fb.onedigit.service.base.BaseCrudService;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
