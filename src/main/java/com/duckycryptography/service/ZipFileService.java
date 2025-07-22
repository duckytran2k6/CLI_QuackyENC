package com.duckycryptography.service;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileService {

    public static void zipFiles(List<File> files, File zipFile) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            int index = 0;
            for (File file : files) {
                if (!file.exists() || !file.canRead()) {
                    continue;
                }

                try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
                    String entryName = index++ + "_" + file.getName();
                    ZipEntry zipEntry = new ZipEntry(entryName);
                    zos.putNextEntry(zipEntry);

                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = bis.read(bytes)) >= 0) {
                        zos.write(bytes, 0, length);
                    }
                }
            }
        }
    }

    public static File prepareZipFile(File sessionDir, String zipFileName) throws Exception {
        if (!sessionDir.isDirectory()) {
            System.out.println("Expected a directory!");
        }
        File downloadDir = DownloadFilesService.getDownloadDir();
        File zipFile = new File(downloadDir, zipFileName);

        File[]  filesToZipList = sessionDir.listFiles();
        if (filesToZipList == null || filesToZipList.length == 0) {
            System.out.println("No files in the directory to zip!");
        }

        zipFiles(Arrays.asList(filesToZipList), zipFile);

        if (!zipFile.exists()) {
            throw new Exception("Failed to create encrypted zip file.");
        }

        return zipFile;
    }

    public static void postZipFileCleanUp(File sessionDir) {
        File[] sessionContents = sessionDir.listFiles();
        if (sessionContents != null) {
            for (File file : sessionContents) {
                postZipFileCleanUp(file);
            }
            System.out.println("Successfully cleaned up zip file.");
        }
        sessionDir.delete();
    }
}
