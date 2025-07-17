package com.duckycryptography.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileService {

    public static void zipFiles(List<File> files, File zipFile) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos)) {

            for (File file : files) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry zipEntry = new ZipEntry(file.getName());

                    zos.putNextEntry(zipEntry);

                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = fis.read(bytes)) >= 0) {
                        zos.write(bytes, 0, length);
                    }
                }
            }
        }
    }

    public static File prepareZipFile(File... filesToZip) throws Exception {
        String zipFileName = "encrypted.zip";
        File downloadDir = DownloadFilesService.getDownloadDir();
        File zipFile = new File(downloadDir, zipFileName);

        List<File>  filesToZipList = Arrays.asList(filesToZip);
        zipFiles(filesToZipList, zipFile);

        return zipFile;
    }

    public static void postZipFileCleanUp(File file) {
        if (file.exists() && file != null && file.delete()) {
            System.out.println("Successfully delete temp file: " + file.getAbsolutePath());
        } else {
            System.err.println("Failed to delete temp file: " + file.getAbsolutePath());
        }
    }
}
