package com.duckycryptography.service;

import com.duckycryptography.core.Decrypt;
import com.duckycryptography.core.DecryptedKeyIV;
import com.duckycryptography.core.PasswordDeriveUtils;
import com.duckycryptography.core.RSAUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.io.File;
import java.nio.file.Files;
import java.security.PrivateKey;
import java.util.List;
import java.util.UUID;

public class    DecryptService {
    private final String TEMP_FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;

    public File decryptWithPassword(List<File> encryptedFile, File IVFile, File saltFile, String password) throws Exception {
        String sessionID = UUID.randomUUID().toString();
        File sessionDir = new File(TEMP_FILE_PATH + sessionID + File.separator);
//          Create the new file directory along with it.
        sessionDir.mkdirs();

        try {
            byte[] saltToByte = Files.readAllBytes(saltFile.toPath());
            byte[] IVToByte = Files.readAllBytes(IVFile.toPath());

            GCMParameterSpec ivSpec = new GCMParameterSpec(128, IVToByte);

            SecretKey aesKey = PasswordDeriveUtils.derivedFromPassword(password, saltToByte);

            for (int i = 0; i < encryptedFile.size(); i++) {
                File inputFile = encryptedFile.get(i);
                if (ValidityCheckerService.checkFile(inputFile, "File #" + (i + 1) + " (" + (inputFile != null ? inputFile.getName() : "unknown") + ")")) {
                    File decryptedFile = new File(sessionDir, inputFile.getName() + ".txt");
                    Decrypt.FileDecrypt(aesKey, ivSpec, inputFile, decryptedFile);
                }
            }

            File zipFile = ZipFileService.prepareZipFile(sessionDir, "decrypted.zip");

            System.out.println("Decrypted successfully! The decrypted zip file is saved to : " + zipFile.getAbsolutePath());

            return zipFile;
        } catch (Exception e) {
            System.err.println("Decryption failed: " + e.getMessage());
            return null;
        }
    }

    public File decryptWithKeyPair(List<File> encryptedFile, File encryptedKeyIV, File privateKeyFile) throws Exception {
        String sessionID = UUID.randomUUID().toString();
        File sessionDir = new File(TEMP_FILE_PATH + sessionID + File.separator);
//          Create the new file directory along with it.
        sessionDir.mkdirs();

        try {
            String encryptedKeyIVString = RSAUtils.loadEncryptedKeyIV(encryptedKeyIV);
            PrivateKey privKey = KeyPairService.loadPrivateKey(privateKeyFile);
            DecryptedKeyIV decryptedKeyIV = RSAUtils.decrypt(encryptedKeyIVString, privKey);

            for (int i = 0; i < encryptedFile.size(); i++) {
                File inputFile = encryptedFile.get(i);
                if (ValidityCheckerService.checkFile(inputFile, "File #" + (i + 1) + " (" + (inputFile != null ? inputFile.getName() : "unknown") + ")")) {
                    File decryptedFile = new File(sessionDir, "decrypted.txt");
                    Decrypt.FileDecrypt(decryptedKeyIV.getSecKey(), decryptedKeyIV.getIV(), inputFile, decryptedFile);
                }
            }

            File zipFile = ZipFileService.prepareZipFile(sessionDir, "decrypted.zip");
            System.out.println("Decrypted successfully! The decrypted zip file is saved to : " + zipFile.getAbsolutePath());

            return zipFile;
        } catch (Exception e) {
            System.err.println("Decryption failed: " + e.getMessage());
            return null;
        }

    }

}
