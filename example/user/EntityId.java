package org.example.user;

import java.util.Objects;

public class EntityId {

    private final String id;

    public EntityId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EntityId entityId = (EntityId) o;
        return id.equals(entityId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
