package com.duckycryptography.service;

import java.io.File;

public class FileValidityService {

    public static boolean checkFile(File file, String name) {
        if (file == null || !file.exists() || !file.isFile()) {
            System.err.println("The " + name + " file is empty/does not exist!");
            return false;
        }
        return true;
    }
}
