package com.fb.onedigit.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity<User> {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;

    @Override
    public void setUpdatableFields(User entity) {
        this.setName(entity.getName());
        this.setEmail(entity.getEmail());
    }
}
