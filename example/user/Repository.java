package org.example.user;

public interface Repository {
    Entity findById(EntityId entityId);

    void update(Entity entity);

    void delete(Entity entity);
}
