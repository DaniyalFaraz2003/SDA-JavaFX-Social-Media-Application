package com.example.sdaprojectsocialmediaapp.utils;


import java.util.regex.Pattern;

public class Validate {
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        // Define the email regex pattern
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        // Compile the regex into a pattern
        Pattern pattern = Pattern.compile(emailRegex);

        // Match the email against the pattern
        return pattern.matcher(email).matches();
    }

    public static boolean isValidName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }

        String nameRegex = "^[A-Za-z ]{3,}$";

        // Compile the regex into a pattern
        Pattern pattern = Pattern.compile(nameRegex);

        // Match the email against the pattern
        return pattern.matcher(nameRegex).matches();
    }

}
