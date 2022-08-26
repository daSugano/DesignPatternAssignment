package org.example;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.example.user.Entity;
import org.example.user.Group;
import org.example.user.GroupRepository;
import org.example.user.JsonConverter;
import org.example.user.TreeSetterFacade;
import org.example.user.User;
import org.example.user.UserRepository;
import org.example.user.Visitor;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {

        var tree = TreeSetterFacade.getInstance();
        var root = tree.buildGroup("ルート", null);
        var lv11 = tree.buildGroup("レベル1-1", root);
        var lv12 = tree.buildGroup("レベル1-2", root);
        var lv13 = tree.buildGroup("レベル1-3", root);
        var lv21 = tree.buildGroup("レベル2-1", lv13);
        var u1 = tree.buildUser("ユーザ1", lv21);
        var u2 = tree.buildUser("ユーザ2", lv21);
        var lv31 = tree.buildGroup("レベル3-1", lv21);
        var lv22 = tree.buildGroup("レベル2-2", lv13);

        var jc = new JsonConverter();

        root.accept(jc);
    }
}
