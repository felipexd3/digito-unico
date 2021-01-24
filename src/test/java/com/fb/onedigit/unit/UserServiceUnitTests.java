package com.fb.onedigit.unit;

import com.fb.onedigit.builders.UserBuilders;
import com.fb.onedigit.models.User;
import com.fb.onedigit.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("Usuários - Unitários")
public class UserServiceUnitTests {

    @Test
    @DisplayName("Deve inserir novo usuário")
    public void shouldInsertUser() {
        UserRepository mock = Mockito.mock(UserRepository.class);
        Mockito.when(mock.save(UserBuilders.builderEntity())).thenReturn(new User());
    }

    @Test
    @DisplayName("Deve Encontrar novo usuário")
    public void shouldFindUser() {
        UserRepository mock = Mockito.mock(UserRepository.class);
        Mockito.when(mock.findByUidAndRemovedAtIsNull(UUID.randomUUID())).thenReturn(Optional.of(new User()));
    }
}
