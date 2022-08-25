package org.example.user;

import java.util.ArrayList;
import java.util.List;

public interface Entity {

    EntityId getId();

    String getName();

    List<Entity> getChildren();

    void addChild(Entity child);

    boolean removeChild(Entity child);

    void defaultMethod(int depth, ArrayList current);

    void accept(Visitor visitor);
}
