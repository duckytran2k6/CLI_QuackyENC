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

public class DecryptService {
    private final File downloadDir = DownloadFilesService.getDownloadDir();

    public File decryptWithPassword(File encryptedFile, File IVFile, File saltFile, String password) throws Exception {

        try {
            byte[] saltToByte = Files.readAllBytes(saltFile.toPath());
            byte[] IVToByte = Files.readAllBytes(IVFile.toPath());

            GCMParameterSpec ivSpec = new GCMParameterSpec(128, IVToByte);

            SecretKey aesKey = PasswordDeriveUtils.derivedFromPassword(password, saltToByte);

            File decryptedFile = new File(downloadDir, "decrypted.txt");
            Decrypt.FileDecrypt(aesKey, ivSpec, encryptedFile, decryptedFile);

            return decryptedFile;
        } catch (Exception e) {
            System.err.println("Decryption failed: " + e.getMessage());
            return null;
        }
    }

    public File decryptWithKeyPair(File encryptedFile, File encryptedKeyIV, File privateKeyFile) throws Exception {

        try {
            String encryptedKeyIVString = RSAUtils.loadEncryptedKeyIV(encryptedKeyIV);

            PrivateKey privKey = KeyPairService.loadPrivateKey(privateKeyFile);

            DecryptedKeyIV decryptedKeyIV = RSAUtils.decrypt(encryptedKeyIVString, privKey);

            File decryptedFile = new File(downloadDir, "decrypted.txt");
            Decrypt.FileDecrypt(decryptedKeyIV.getSecKey(), decryptedKeyIV.getIV(), encryptedFile, decryptedFile);

            return decryptedFile;
        } catch (Exception e) {
            System.err.println("Decryption failed: " + e.getMessage());
            return null;
        }

    }

}
