package com.fb.onedigit.models;

import com.fb.onedigit.models.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OneDigit extends BaseEntity<OneDigit> {
    @Column(nullable = false)
    private String number;
    @Column(nullable = false)
    private Integer exp;
    private Integer digit;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public void setUpdatableFields(OneDigit entity) {
    }
}
