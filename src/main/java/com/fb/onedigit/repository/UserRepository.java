package com.fb.onedigit.repository;

import com.fb.onedigit.models.User;
import org.springframework.stereotype.Repository;

public interface UserRepository extends BaseCrudRepository<User, Long> {
}
