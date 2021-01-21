package com.fb.onedigit.service;

import com.fb.onedigit.exceptions.ApplicationException;
import com.fb.onedigit.exceptions.handler.HandlerException;
import com.fb.onedigit.messages.Messages;
import com.fb.onedigit.models.BaseEntity;
import com.fb.onedigit.repository.BaseCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public abstract class BaseCrudService <T extends BaseEntity<T>, K, R extends BaseCrudRepository<T, K>>{
    @Autowired
    protected R repository;

    protected abstract String getEntityName();
    protected abstract Optional<T> findEntityByProperty(T entity);

    public List<T> findAll() {
        return repository.findAllByRemovedAtIsNull();
    }

    public Optional<T> findByUid(UUID uuid) {
        return repository.findByUidAndRemovedAtIsNull(uuid);
    }

    @Transactional
    public T insert(T entity) {
        findEntityByProperty(entity).ifPresent(
            managedEntity -> {
                throw new ApplicationException(
                    String.format(Messages.ENTITY_ALREADY_EXISTS, getEntityName())
                );
            }
        );

        return repository.save(entity);
    }

    @Transactional
    public T update(T entity) {
        // check if entity already exists
        if (Objects.isNull(entity.getUid())) {
            throw new ApplicationException(
                Messages.ENTITY_UUID_SHOULD_BE_NOT_NULL
            );
        }

        var persistedEntity = repository.findByUidAndRemovedAtIsNull(entity.getUid())
            .orElseThrow(() -> new ApplicationException(
                String.format(Messages.ENTITY_NOT_FOUND, getEntityName())
            ));

        persistedEntity.setUpdatableFields(entity);
        repository.save(persistedEntity);
        return persistedEntity;
    }

    @Transactional
    public void remove(UUID uuid) {
        var persistedEntity = repository.findByUidAndRemovedAtIsNull(uuid)
            .orElseThrow(
                () -> new ApplicationException(
                    Messages.ENTITY_CANNOT_BE_DELETED_BECAUSE_IT_DOESNT_EXIST_OR_IT_WAS_ALREADY_DELETE
                )
            );

        persistedEntity.setRemovedAt(LocalDateTime.now());
        repository.save(persistedEntity);
    }
}
