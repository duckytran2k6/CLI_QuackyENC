package com.duckycryptography.core;

import com.duckycryptography.service.CleanUpService;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.File;
import java.nio.file.Files;
import java.security.*;
import java.util.Base64;

public class RSAUtils {

    public static KeyPair generateKeyPairs() throws Exception{
        KeyPairGenerator pairKeys = KeyPairGenerator.getInstance("RSA");
        pairKeys.initialize(2048);
        return pairKeys.generateKeyPair();
    }

    public static void saveEncryptedKey(String encryptedKey, File file) throws Exception {
        Files.writeString(file.toPath(), encryptedKey);
    }

    public static String loadEncryptedKey(File file) throws Exception {
        return Files.readString(file.toPath());
    }

    public static String encrypt(SecretKey key, PublicKey publicKey) throws Exception {
        byte[] keyByte = key.getEncoded();

        Cipher encryptCip = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        encryptCip.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encrypted = encryptCip.doFinal(keyByte);

        String encryptedKey = Base64.getEncoder().encodeToString(encrypted);

        return encryptedKey;
    }

    public static DecryptedKey decrypt(String encryptedKey, PrivateKey privateKey) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedKey);

        Cipher decryptCip = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        decryptCip.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] keyByte = decryptCip.doFinal(encryptedBytes);

        CleanUpService.authCleanUp(encryptedBytes);

        SecretKey SecKey = new SecretKeySpec(keyByte, "AES");

        return new DecryptedKey(SecKey);
    }

}
