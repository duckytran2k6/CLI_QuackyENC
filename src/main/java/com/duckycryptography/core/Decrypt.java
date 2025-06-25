package com.duckycryptography.core;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Decrypt {

    public static void FileDecrypt(SecretKey key, GCMParameterSpec IV, File inFile, File outFile) throws Exception{

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

}
