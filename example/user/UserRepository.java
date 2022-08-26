package org.example.user;

import java.util.HashMap;
import java.util.Map;

public class UserRepository implements Repository {

    private static final UserRepository SINGLETON = new UserRepository();

    public static final UserRepository getInstance() {
        return SINGLETON;
    }

    private final Map<EntityId, User> instances = new HashMap<>();

    private UserRepository() {

    }

    public User findById(EntityId entityId) {
        return instances.get(entityId);
    }

    public void update(User user) {
        instances.put(user.getId(), user);
    }

    public void delete(User user) {
        instances.remove(user.getId());
    }
}
