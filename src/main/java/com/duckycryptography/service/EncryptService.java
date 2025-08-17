package com.duckycryptography.service;

import com.duckycryptography.core.Encrypt;
import com.duckycryptography.core.PasswordDeriveUtils;
import com.duckycryptography.core.RSAUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.io.File;
import java.io.FileOutputStream;
import java.security.PublicKey;
import java.util.List;
import java.util.UUID;

public class EncryptService {
    private final String TEMP_FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;

    public File encryptDataWithPassword(List<File> files, char[] password) {

        String sessionID = UUID.randomUUID().toString();
        File sessionDir = new File(TEMP_FILE_PATH + sessionID + File.separator);
        sessionDir.mkdirs();

        File ivFile = new File(sessionDir,"IV.txt");
        File saltFile = new File(sessionDir,"salt.txt");

        try {
            byte[] salt = Encrypt.generateSalt();
            SecretKey aesKey = PasswordDeriveUtils.derivedFromPassword(password, salt);
            GCMParameterSpec IV = Encrypt.genIV();

            for (int i = 0; i < files.size(); i++) {
                File inputFile = files.get(i);
                if (ValidityCheckerService.checkFile(inputFile, "File #" + (i + 1) + " (" + (inputFile != null ? inputFile.getName() : "unknown") + ")")) {
                    String newFileName = FilesService.renamePlainFile(inputFile.getName());
                    File encryptedFile = new File(sessionDir,newFileName);
                    Encrypt.FileEncrypt(aesKey, IV, inputFile, encryptedFile);
                }
            }

            try (FileOutputStream IVoutput = new FileOutputStream(ivFile)) {
                IVoutput.write(IV.getIV());
            }

            try (FileOutputStream Saltoutput = new FileOutputStream(saltFile)) {
                Saltoutput.write(salt);
            }

            File zipFile = ZipFileService.prepareZipFile(sessionDir, "encrypted.zip");

            if (zipFile == null) {
                System.out.println("Encryption failed: no files have been added to the zip file!");
                return null;
            } else {
                System.out.println("Encryption successfully! The encrypted zip file is saved to : " + zipFile.getAbsolutePath());
                return zipFile;
            }
        } catch (Exception e) {
            System.err.println("Encryption failed: " + e.getMessage());
            return null;
        } finally {
            CleanUpService.tempSessionDirCleanUp(sessionDir);
        }
    }

    public File encryptDataWithKeyPair(List<File> files, File key) {

        String sessionID = UUID.randomUUID().toString();
        File sessionDir = new File(TEMP_FILE_PATH + sessionID + File.separator);
        sessionDir.mkdirs();

        File encKeyFile = new File(sessionDir, "encrypted_Key.txt");
        File ivFile = new File(sessionDir,"IV.txt");

        try {
            SecretKey aesKey = Encrypt.SecKey();
            GCMParameterSpec IV = Encrypt.genIV();

            for (int i = 0; i < files.size(); i++) {
                File inputFile = files.get(i);
                if (ValidityCheckerService.checkFile(inputFile, "File #" + (i + 1) + " (" + (inputFile != null ? inputFile.getName() : "unknown") + ")")) {
                    String newFileName = FilesService.renamePlainFile(inputFile.getName());
                    File encryptedFile = new File(sessionDir,newFileName);
                    Encrypt.FileEncrypt(aesKey, IV, inputFile, encryptedFile);
                }
            }

            PublicKey publicKey = KeyPairService.loadPublicKey(key);
            String encryptedKey = RSAUtils.encrypt(aesKey, publicKey);
            RSAUtils.saveEncryptedKey(encryptedKey, encKeyFile);

            try (FileOutputStream IVoutput = new FileOutputStream(ivFile)) {
                IVoutput.write(IV.getIV());
            }

            File zipFile = ZipFileService.prepareZipFile(sessionDir, "encrypted.zip");

            if (zipFile == null) {
                System.out.println("Encryption failed: no files have been added to the zip file!");
                return null;
            } else {
                System.out.println("Encryption successfully! The encrypted zip file is saved to : " + zipFile.getAbsolutePath());
                return zipFile;
            }
        } catch (Exception e) {
            System.err.println("Encryption failed: " + e.getMessage());
            return null;
        } finally {
            CleanUpService.tempSessionDirCleanUp(sessionDir);
        }
    }
}
