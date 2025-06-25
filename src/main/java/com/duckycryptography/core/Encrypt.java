package com.duckycryptography.core;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.SecureRandom;

public class Encrypt {

    //  Generate the secret key
    public static SecretKey SecKey() throws Exception {
        KeyGenerator Keygenerator = KeyGenerator.getInstance("AES");
        Keygenerator.init(256);
        return Keygenerator.generateKey();
    }

    //  Generate the IV
    public static GCMParameterSpec genIV() {
        byte[] IV = new byte[12];
        SecureRandom random = new SecureRandom();
        random.nextBytes(IV);
        return new GCMParameterSpec(128, IV);
    }

    public static void FileEncrypt(SecretKey key, GCMParameterSpec IV, File inFile, File outFile) throws Exception {

        Cipher encryptionCip = Cipher.getInstance("AES/GCM/NoPadding");
        encryptionCip.init(Cipher.ENCRYPT_MODE, key, IV);

        FileInputStream inputStream = new FileInputStream(inFile);
        FileOutputStream outputStream = new FileOutputStream(outFile);

        byte[] buffer = new byte[64];
        int byteRead;

        while((byteRead = inputStream.read(buffer)) != -1) {
            byte[] output =   encryptionCip.update(buffer, 0, byteRead) ;
            if (output != null) {
                outputStream.write(output);
            }
        }

        byte[] outputBytes = encryptionCip.doFinal();
        if(outputBytes != null) {
            outputStream.write(outputBytes);
        }

        inputStream.close();
        outputStream.close();
    }

    public static byte[] generateSalt() throws Exception {
        SecureRandom saltRandom = new SecureRandom();
        byte[] salt = new byte[16];
        saltRandom.nextBytes(salt);
        return salt;
    }

}
