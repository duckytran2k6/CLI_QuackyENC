package com.duckycryptography.service;

public class PasswordChecker {

    public static boolean validPassword(String password) {
        return (password.length() < 8 || password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).+$"));
    }
}
