package com.example.sdaprojectsocialmediaapp.models;

import java.util.ArrayList;

public class Student {
    private int id;
    private String name;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private ArrayList<Student> friends;

    public Student(String name, String userName, String password, String email, String phone) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Student> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Student> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        StringBuilder friendsList = new StringBuilder();
        if (friends != null && !friends.isEmpty()) {
            for (Student friend : friends) {
                friendsList.append(friend.getUserName()).append(", ");
            }
            // Remove the last comma and space
            friendsList.setLength(friendsList.length() - 2);
        } else {
            friendsList.append("No friends");
        }

        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", friends=[" + friendsList.toString() + "]" +
                '}';
    }

}
