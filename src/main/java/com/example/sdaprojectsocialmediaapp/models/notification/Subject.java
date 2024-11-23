package com.example.sdaprojectsocialmediaapp.models.notification;

import java.util.ArrayList;

public interface Subject {
    ArrayList<Observer> observers = new ArrayList<>();
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Notification notification);
}
