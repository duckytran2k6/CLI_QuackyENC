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
                try (FileInputStream fis = new FileInputStream(file);) {
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
        String TEMP_FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;
        File zipFile = new File(TEMP_FILE_PATH + "encryptedData.zip");

        List<File>  filesToZipList = Arrays.asList(filesToZip);
        zipFiles(filesToZipList, zipFile);

        return zipFile;
    }
}
