package com.duckycryptography.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class LoadFilesService {

    public static File loadFiles(MultipartFile uploadFile, String fileName) throws IOException {
        String TEMP_FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;
        File file = new File(TEMP_FILE_PATH + fileName);
        uploadFile.transferTo(file);
        return file;
    }

}
