package com.duckycryptography.service;

import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DownloadFilesService {

    public static void moveToDest(String userDest, File zipFile) throws IOException {
        File outputDest = new File(userDest);

        if (!outputDest.exists()) {
            outputDest.mkdirs();
        }

        Path destPath = Path.of(outputDest.getAbsolutePath(), zipFile.getName());
        Files.copy(zipFile.toPath(), destPath);

        cleanUpTempDir(zipFile.getParentFile());
    }

    public static void cleanUpTempDir(File tempFolder) throws IOException {
        if (tempFolder.exists()) {
            FileSystemUtils.deleteRecursively(tempFolder);
        }
    }
}
