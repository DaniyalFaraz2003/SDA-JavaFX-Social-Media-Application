package com.example.sdaprojectsocialmediaapp.models;

import java.util.ArrayList;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String address;
    private String phone;
    private ArrayList<Student> friends;

    public Student(String firstName, String lastName, String userName, String password, String email, String address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", friends=[" + friendsList.toString() + "]" +
                '}';
    }

}
