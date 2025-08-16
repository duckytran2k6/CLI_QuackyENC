package com.duckycryptography.service;

import java.io.File;
import java.util.List;

public class ValidityCheckerService {
    private final static long MAX_FILE_SIZE = 100 * 1024 * 1024; // 100 MB
    private final static int MAX_FILES = 20;

    public static boolean checkListLimit(List<File> files) {
        if (files == null) {
            System.err.println("Please upload a valid list of files!");
            return false;
        }

        if (files.isEmpty()) {
            System.err.println("File list is empty!");
            return false;
        }
        if (files.size() > MAX_FILES) {
            System.err.println("You can only upload up to " + MAX_FILES + " files!");
            return false;
        }
        for (File file : files) {
            if (file.length() > MAX_FILE_SIZE) {
                System.err.println("The file " + file.getName() + "exceeds " + MAX_FILE_SIZE + "MB!");
                return false;
            }
        }
        return true;
    }

    public static boolean checkFile(File file, String name) {
        String fileName = file.getName();
        if (fileName.endsWith(".txt") || fileName.endsWith("public.key") || fileName.endsWith(".enc") || fileName.endsWith("private.key")) {
            System.err.println("Valid file types: " + fileName);
            return true;
        } else if (!file.exists() || file.length() == 0 || !file.isFile()) {
            System.err.println("The " + name + " file is empty/does not exist!");
            return false;
        } else {
            System.err.println("The file " + file.getName() + " is not a valid file type!");
        }
        return false;
    }

    public static boolean checkFileExists(File file, String fileName) {
        return file.getName().toLowerCase().endsWith(fileName);
    }

    public static boolean validPassword(char[] password) {
        if (password.length < 8) {
            System.err.println("Password must be at least 8 characters!");
            return false;
        }

        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password) {
            if (Character.isLowerCase(c)) {
                hasLower = true;
            } else {
                System.out.println("Must have at least one lowercase letter!");
            }
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else {
                System.out.println("Must have at least one uppercase letter!");
            }

            if (Character.isDigit(c)) {
                hasDigit = true;
            } else {
                System.out.println("Must have at least one digit (0-9)!");
            }

            if (hasLower) {
                hasSpecial = true;
            } else {
                System.out.println("Must have at least one special letter!");
            }
        }

        return hasLower && hasUpper && hasDigit && hasSpecial;
    }
}
