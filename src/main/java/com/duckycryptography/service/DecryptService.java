package com.duckycryptography.service;

import com.duckycryptography.core.Decrypt;
import com.duckycryptography.core.DecryptedKey;
import com.duckycryptography.core.PasswordDeriveUtils;
import com.duckycryptography.core.RSAUtils;

import javax.crypto.SecretKey;
import java.io.File;
import java.nio.file.Files;
import java.security.PrivateKey;
import java.util.List;
import java.util.UUID;

public class DecryptService {
    private final String TEMP_FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;

    public File decryptWithPassword(List<File> encryptedFile, File ivFile, File saltFile, char[] password) throws Exception {
        String sessionID = UUID.randomUUID().toString();
        File sessionDir = new File(TEMP_FILE_PATH + sessionID + File.separator);
        sessionDir.mkdirs();

        try {
            byte[] saltToByte = Files.readAllBytes(saltFile.toPath());

            SecretKey aesKey = PasswordDeriveUtils.derivedFromPassword(password, saltToByte);
            CleanUpService.passwordWipe(password);

            for (int i = 0; i < encryptedFile.size(); i++) {
                File inputFile = encryptedFile.get(i);
                if (ValidityCheckerService.checkFile(inputFile, "File #" + (i + 1) + " (" + (inputFile != null ? inputFile.getName() : "unknown") + ")")) {
                    String newFileName = FilesService.renameEncryptedFile(inputFile.getName());
                    File decryptedFile = new File(sessionDir, newFileName);
                    Decrypt.FileDecrypt(aesKey, ivFile, inputFile, decryptedFile);
                }
            }

            File zipFile = ZipFileService.prepareZipFile(sessionDir, "decrypted.zip");

            System.out.println("Decrypted successfully! The decrypted zip file is saved to : " + zipFile.getAbsolutePath());

            return zipFile;
        } catch (Exception e) {
            System.err.println("Decryption failed: " + e.getMessage());
            return null;
        } finally {
            CleanUpService.tempSessionDirCleanUp(sessionDir);
        }
    }

    public File decryptWithKeyPair(List<File> encryptedFile, File encryptedKey, File ivFile, File privateKeyFile) throws Exception {
        String sessionID = UUID.randomUUID().toString();
        File sessionDir = new File(TEMP_FILE_PATH + sessionID + File.separator);
        sessionDir.mkdirs();

        try {
            String encryptedKeyString = RSAUtils.loadEncryptedKey(encryptedKey);
            PrivateKey privKey = KeyPairService.loadPrivateKey(privateKeyFile);
            DecryptedKey decryptedKey = RSAUtils.decrypt(encryptedKeyString, privKey);

            for (int i = 0; i < encryptedFile.size(); i++) {
                File inputFile = encryptedFile.get(i);
                if (ValidityCheckerService.checkFile(inputFile, "File #" + (i + 1) + " (" + (inputFile != null ? inputFile.getName() : "unknown") + ")")) {
                    String newFileName = FilesService.renameEncryptedFile(inputFile.getName());
                    File decryptedFile = new File(sessionDir, newFileName);
                    Decrypt.FileDecrypt(decryptedKey.getSecKey(), ivFile, inputFile, decryptedFile);
                }
            }

            File zipFile = ZipFileService.prepareZipFile(sessionDir, "decrypted.zip");
            System.out.println("Decrypted successfully! The decrypted zip file is saved to : " + zipFile.getAbsolutePath());

            return zipFile;
        } catch (Exception e) {
            System.err.println("Decryption failed: " + e.getMessage());
            return null;
        } finally {
            CleanUpService.tempSessionDirCleanUp(sessionDir);
        }
    }

}
