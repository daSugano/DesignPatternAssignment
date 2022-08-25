package org.example.user;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class User implements Entity {

    public static class Builder {

        private EntityId entityId = new EntityId(UUID.randomUUID().toString());

        private String name;

        private Builder() {

        }

        public Builder setEntityId(EntityId entityId) {
            this.entityId = entityId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final EntityId entityId;

    private final String name;

    private User(Builder builder) {
        this.entityId = builder.entityId;
        this.name = builder.name;
    }

    @Override
    public EntityId getId() {
        return entityId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Entity> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public void addChild(Entity child) {

    }

    @Override
    public boolean removeChild(Entity child) {
        return false;
    }

    public void defaultMethod(int depth, ArrayList current) {
        LinkedHashMap m = new LinkedHashMap();
        m.put("name", name);
        current.add(m);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
