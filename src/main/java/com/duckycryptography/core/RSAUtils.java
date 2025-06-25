package com.duckycryptography.core;

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

    public static void saveEncryptedKeyIV(String encryptedKeyIV, File file) throws Exception {
        Files.writeString(file.toPath(), encryptedKeyIV);
    }

    public static String loadEncryptedKeyIV(File file) throws Exception {
        return Files.readString(file.toPath());
    }

    public static String encrypt(SecretKey key, GCMParameterSpec IV, PublicKey publicKey) throws Exception {
        byte[] keyByte = key.getEncoded();
        byte[] IVByte = IV.getIV();
        byte[] combined = new byte[keyByte.length + IVByte.length];

        System.arraycopy(keyByte, 0, combined, 0, keyByte.length);
        System.arraycopy(IVByte, 0, combined, keyByte.length, IVByte.length);

        Cipher encryptCip = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        encryptCip.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encrypted = encryptCip.doFinal(combined);

        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static DecryptedKeyIV decrypt(String encryptedData, PrivateKey privateKey) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);

        Cipher decryptCip = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        decryptCip.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] combined = decryptCip.doFinal(encryptedBytes);

        byte[] keyByte = new byte[32];
        byte[] IVByte = new byte[12];

        System.arraycopy(combined, 0, keyByte, 0, 32);
        System.arraycopy(combined, 32, IVByte, 0, 12);

        SecretKey SecKey = new SecretKeySpec(keyByte, "AES");
        GCMParameterSpec IV = new GCMParameterSpec(128, IVByte);

        return new DecryptedKeyIV(SecKey, IV);
    }

}
