package com.duckycryptography.service;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilesSelectService {

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
}
