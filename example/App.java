package org.example;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.example.user.Entity;
import org.example.user.Group;
import org.example.user.GroupRepository;
import org.example.user.JsonConverter;
import org.example.user.User;
import org.example.user.UserRepository;
import org.example.user.Visitor;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        var groupRepository = GroupRepository.getInstance();
        var userRepository = UserRepository.getInstance();

        var root = Group.builder().setName("ルート").build();
        groupRepository.update(root);

        var lv11 = Group.builder().setName("レベル1-1").build();
        groupRepository.update(lv11);
        root.addChild(lv11);

        var lv12 = Group.builder().setName("レベル1-2").build();
        groupRepository.update(lv12);
        root.addChild(lv12);

        var lv13 = Group.builder().setName("レベル1-3").build();
        groupRepository.update(lv13);
        root.addChild(lv13);

        var lv21 = Group.builder().setName("レベル2-1").build();
        groupRepository.update(lv21);
        lv13.addChild(lv21);

        var u1 = User.builder().setName("ユーザー1").build();
        userRepository.update(u1);
        lv21.addChild(u1);

        var u2 = User.builder().setName("ユーザー2").build();
        userRepository.update(u2);
        lv21.addChild(u2);

        var lv31 = Group.builder().setName("レベル3-1").build();
        groupRepository.update(lv31);
        lv21.addChild(lv31);

        var lv22 = Group.builder().setName("レベル2-2").build();
        groupRepository.update(lv22);
        lv13.addChild(lv22);

        var jc = new JsonConverter();

        root.accept(jc);
    }
}
