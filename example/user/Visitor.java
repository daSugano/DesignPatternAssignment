package org.example.user;

public interface Visitor {

    void visit(Group group);

    void visit(User user);
}
