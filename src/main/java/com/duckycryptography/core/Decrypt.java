package com.duckycryptography.core;

import com.duckycryptography.service.CleanUpService;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;

public class Decrypt {

    public static void FileDecrypt(SecretKey key, File ivFile, File inFile, File outFile) throws Exception {

        GCMParameterSpec IV = Decrypt.loadIV(ivFile);

        Cipher decryptionCip = Cipher.getInstance("AES/GCM/NoPadding");
        decryptionCip.init(Cipher.DECRYPT_MODE, key, IV);

        FileInputStream inputStream = new FileInputStream(inFile);
        FileOutputStream outputStream = new FileOutputStream(outFile);

        byte[] buffer = new byte[64];
        int byteRead;

        while ((byteRead = inputStream.read(buffer)) != -1) {
            byte[] output = decryptionCip.update(buffer, 0, byteRead);
            if (output != null) {
                outputStream.write(output);
            }
        }

        byte[] finalBytes = decryptionCip.doFinal();
        if (finalBytes != null) {
            outputStream.write(finalBytes);
        }

        inputStream.close();
        outputStream.close();
    }

    public static GCMParameterSpec loadIV (File ivFile) throws Exception{
        byte[] IVToByte = Files.readAllBytes(ivFile.toPath());

        GCMParameterSpec IV = new GCMParameterSpec(128, IVToByte);

        CleanUpService.authCleanUp(IVToByte);

        return IV;
    }

}
