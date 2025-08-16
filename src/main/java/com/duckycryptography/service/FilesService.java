package com.duckycryptography.service;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilesService {

    public static List<File> selectMultipleFiles(String title) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle(title);
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File [] selectedFiles = chooser.getSelectedFiles();
            return new ArrayList<>(Arrays.asList(selectedFiles));
        }
        return null;
    }

    public static String renamePlainFile(String fileName) {
        String newFileName;
        if (fileName.endsWith(".txt")) {
            newFileName = fileName.substring(0, fileName.length() - 4) + ".enc";
        } else {
            newFileName = fileName + ".enc";
        }
        return newFileName;
    }

    public static String renameEncryptedFile(String fileName) {
        String newFileName;
        if (fileName.endsWith(".enc")) {
            fileName = fileName.replaceFirst("^\\d+_", "");
            newFileName = fileName.substring(0, fileName.length() - 4) + ".txt";
        } else {
            newFileName = fileName;
        }
        return newFileName;
    }
}
