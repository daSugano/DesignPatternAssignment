package org.example.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

public class Group implements Entity {

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

        public Group build() {
            return new Group(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private final EntityId entityId;

    private final String name;

    private Group(Builder builder) {
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
        return GroupTreeMediator.getInstance().getChildren(this);
    }

    @Override
    public void addChild(Entity child) {
        GroupTreeMediator.getInstance().addChild(this, child);
    }

    @Override
    public boolean removeChild(Entity child) {
        return GroupTreeMediator.getInstance().removeChild(this, child);
    }

    @Override
    public void defaultMethod(int depth, ArrayList current) {
        LinkedHashMap m = new LinkedHashMap();
        m.put("name", name);
        m.put("children", new ArrayList());
        current.add(m);
        var c = (HashMap) current.get(current.size() - 1);
        current = (ArrayList) c.get("children");
        for (Entity e : getChildren()) {
            e.defaultMethod(depth + 1, current);
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        // getChildren().forEach(entity -> entity.accept(visitor));
    }
}
