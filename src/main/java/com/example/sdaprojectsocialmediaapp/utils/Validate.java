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

        String nameRegex = "^[A-Za-z]+( [A-Za-z]+)*$";

        // Compile the regex into a pattern
        Pattern pattern = Pattern.compile(nameRegex);

        // Match the email against the pattern
        return pattern.matcher(name).matches();
    }

    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }

        // Define the phone regex pattern (for example, to allow a 10-digit number)
        String phoneRegex = "^[0-9]{10}$"; // Adjust this regex as per the required phone format

        // Compile the regex into a pattern
        Pattern pattern = Pattern.compile(phoneRegex);

        // Match the phone number against the pattern
        return pattern.matcher(phone).matches();
    }

    public static boolean isValidUsername(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }

        // Define the username regex pattern
        String usernameRegex = "^i(21|22|23|24)[0-9]{4}$";

        // Compile the regex into a pattern
        Pattern pattern = Pattern.compile(usernameRegex);

        // Match the username against the pattern
        return pattern.matcher(username).matches();
    }

}
