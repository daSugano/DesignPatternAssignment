package org.example.user;

public class TreeSetterFacade {

    private static final TreeSetterFacade SINGLETON = new TreeSetterFacade();

    public static final TreeSetterFacade getInstance() {
        return SINGLETON;
    }

    private GroupRepository groupRepository;
    private UserRepository userRepository;

    private TreeSetterFacade() {
        this.groupRepository = GroupRepository.getInstance();
        this.userRepository = UserRepository.getInstance();
    }

    public Group buildGroup(String name, Entity parent) {
        var self = Group.builder().setName(name).build();
        this.groupRepository.update(self);
        if (parent != null) {
            parent.addChild(self);
        }

        return self;
    }

    public User buildUser(String name, Entity parent) {
        var self = User.builder().setName(name).build();
        this.userRepository.update(self);
        if (parent != null) {
            parent.addChild(self);
        }
        return self;
    }

}
