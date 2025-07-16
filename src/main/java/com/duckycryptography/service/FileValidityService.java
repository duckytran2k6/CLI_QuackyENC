package com.duckycryptography.service;

import java.io.File;
import java.util.List;

public class FileValidityService {
    private final static long MAX_FILE_SIZE = 100 * 1024 * 1024; // 100 MB
    private final static int MAX_FILES = 20;

    public static boolean checkListLimit(List<File> files) {
        if (files.isEmpty()) {
            System.err.println("File list is empty");
            return false;
        }
        if (files.size() > MAX_FILES) {
            System.err.println("You can only upload up to " + MAX_FILES + " files!");
            return false;
        }
        for (File file : files) {
            if (file.length() > MAX_FILE_SIZE) {
                System.err.println("The file " + file.getName() + "exceeds " + MAX_FILE_SIZE + "MB");
                return false;
            }
        }
        return true;
    }

    public static boolean checkFile(File file, String name) {
        if (file == null || !file.exists() || !file.isFile()) {
            System.err.println("The " + name + " file is empty/does not exist!");
            return false;
        }
        return true;
    }
}
