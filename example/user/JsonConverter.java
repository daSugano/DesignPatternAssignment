package org.example.user;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonConverter implements Visitor {
    int depth = 0;
    ArrayList current = new ArrayList();

    public JsonConverter() {

    }

    // FIXMEgroup向けのvisitとuser向けのvisitに棲み分けできていない

    @Override
    public void visit(Group group) {
        group.defaultMethod(depth, current);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(current);
        System.out.println(json);
        depth++;
    }

    @Override
    public void visit(User user) {
        user.defaultMethod(depth, current);
    }
}
