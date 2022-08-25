package org.example.user;

import java.util.HashMap;
import java.util.Map;

public class GroupRepository {

    private static final GroupRepository SINGLETON = new GroupRepository();

    public static GroupRepository getInstance() {
        return SINGLETON;
    }

    private final Map<EntityId, Group> instances = new HashMap<>();

    private GroupRepository() {

    }

    public Group findById(EntityId entityId) {
        return instances.get(entityId);
    }

    public void update(Group group) {
        instances.put(group.getId(), group);
    }

    public void delete(Group group) {
        instances.remove(group.getId());
    }
}
