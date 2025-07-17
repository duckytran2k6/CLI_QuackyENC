package com.duckycryptography.service;

import java.io.File;

public class DownloadFilesService {

    public static File getDownloadDir() {
        String userHome = System.getProperty("user.home");
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            return new File(userHome, "Downloads");
        } else if (os.contains("mac")) {
            return new File(userHome, "Downloads");
        } else if (os.contains("nux") || os.contains("nix") || os.contains("aix")) {
            return new File(userHome, "Downloads");
        } else {
            return new File(userHome);
        }
    }
}
