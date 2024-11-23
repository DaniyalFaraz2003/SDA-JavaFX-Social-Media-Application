package com.example.sdaprojectsocialmediaapp.controllers.chat;

public class StaticToID {
    private static int id;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        StaticToID.id = id;
    }
}
