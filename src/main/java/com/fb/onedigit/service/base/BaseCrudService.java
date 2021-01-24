package com.fb.onedigit.service.base;

import com.fb.onedigit.exceptions.ApplicationException;
import com.fb.onedigit.messages.Messages;
import com.fb.onedigit.models.base.BaseEntity;
import com.fb.onedigit.repository.base.BaseCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class BaseCrudService<T extends BaseEntity<T>, K, R extends BaseCrudRepository<T, K>> {
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

    public T insert(T entity) {
        findEntityByProperty(entity).ifPresent(
            managedEntity -> {
                throw new ApplicationException(
                    String.format(Messages.ENTITY_ALREADY_EXISTS, getEntityName()), HttpStatus.CONFLICT
                );
            }
        );
        return repository.save(entity);
    }

    public T update(T entity) {
        var persistedEntity = repository.findByUidAndRemovedAtIsNull(entity.getUid())
            .orElseThrow(() -> new ApplicationException(
                String.format(Messages.ENTITY_NOT_FOUND, getEntityName())
            ));
        persistedEntity.setUpdatableFields(entity);
        repository.save(persistedEntity);
        return persistedEntity;
    }

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
