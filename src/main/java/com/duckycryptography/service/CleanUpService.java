package com.duckycryptography.service;

import java.io.File;
import java.util.Arrays;

public class CleanUpService {

    public static void tempSessionDirCleanUp(File sessionDir) {
        File[] sessionContents = sessionDir.listFiles();
        if (sessionContents != null) {
            for (File file : sessionContents) {
                tempSessionDirCleanUp(file);
            }
            System.out.println("Successfully cleaned up temp session files.");
        }
        sessionDir.delete();
    }

    public static void authCleanUp(byte[] data) {
        if (data != null) {
            Arrays.fill(data, (byte) 0);
            data = null;
        }
    }

    public static void passwordWipe(char[] password) {
        if (password != null) {
            Arrays.fill(password, '\0');
        }
    }
}
