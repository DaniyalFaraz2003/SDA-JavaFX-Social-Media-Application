package com.example.sdaprojectsocialmediaapp.models;

import com.example.sdaprojectsocialmediaapp.models.notification.Friend;
import com.example.sdaprojectsocialmediaapp.models.notification.Notification;
import com.example.sdaprojectsocialmediaapp.models.notification.Observer;
import com.example.sdaprojectsocialmediaapp.models.notification.Subject;

import java.util.ArrayList;

public class Student implements Subject {
    private int id;
    private String name;
    private String userName;
    private String password;
    private String email;
    private String phone;
    private ArrayList<Observer> friends;

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

    public ArrayList<Observer> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Observer> friends) {
        this.friends = friends;
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers(Notification notification) {
        for (Observer observer : observers) {
            observer.notify(notification);
        }
    }
}
