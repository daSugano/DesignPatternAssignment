package org.example.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class GroupTreeMediator {

    private static final GroupTreeMediator SINGLETON = new GroupTreeMediator();

    static GroupTreeMediator getInstance() {
        return SINGLETON;
    }

    private class Relationship {

        private final EntityId parent;

        private final EntityId child;

        private Relationship(EntityId parent, EntityId child) {
            this.parent = parent;
            this.child = child;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Relationship that = (Relationship) o;
            return parent.equals(that.parent) && child.equals(that.child);
        }

        @Override
        public int hashCode() {
            return Objects.hash(parent, child);
        }
    }

    private final GroupRepository groupRepository = GroupRepository.getInstance();

    private final UserRepository userRepository = UserRepository.getInstance();

    // FIXME Listの実装では検索がリニアに遅くなる
    private List<Relationship> relationshipRepository = new ArrayList<>();

    private GroupTreeMediator() {

    }

    public List<Entity> getChildren(Entity parent) {
        return relationshipRepository.stream()
                .filter(r -> parent.getId().equals(r.parent))
                .map(r -> {
                    var group = groupRepository.findById(r.child);
                    if (group != null) {
                        return group;
                    }
                    return userRepository.findById(r.child);
                })
                .collect(Collectors.toList());
    }

    public void addChild(Entity parent, Entity child) {
        var relationship = new Relationship(parent.getId(), child.getId());
        if (relationshipRepository.contains(relationship)) {
            return;
        }
        relationshipRepository.add(relationship);
    }

    public boolean removeChild(Entity parent, Entity child) {
        var relationship = new Relationship(parent.getId(), child.getId());
        return relationshipRepository.remove(relationship);
    }
}
