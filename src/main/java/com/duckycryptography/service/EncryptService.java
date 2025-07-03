package com.duckycryptography.service;

import com.duckycryptography.core.Encrypt;
import com.duckycryptography.core.PasswordDeriveUtils;
import com.duckycryptography.core.RSAUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.io.File;
import java.io.FileOutputStream;
import java.security.PublicKey;
import java.util.UUID;

public class EncryptService {
    //    Using the .getProperty() method to retrieve the value of a system property (ex. Window/Mac/Linux) then use the java property to create a temp folder directory.
    private final String TEMP_FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;

    public File encryptDataWithPassword(File file, String password) throws Exception {

        String sessionID = UUID.randomUUID().toString();
        File tempFile = new File(TEMP_FILE_PATH + sessionID);
//          Create the new file directory along with it.
        tempFile.mkdirs();

        byte[] salt = Encrypt.generateSalt();
        SecretKey aesKey = PasswordDeriveUtils.derivedFromPassword(password, salt);
        GCMParameterSpec IV = Encrypt.genIV();

        File encryptedFile = new File(TEMP_FILE_PATH + "encrypted.enc");
        Encrypt.FileEncrypt(aesKey, IV, file, encryptedFile);

        File IVFile = new File(TEMP_FILE_PATH + "IV.txt");
        try (FileOutputStream IVoutput = new FileOutputStream(IVFile)) {
            IVoutput.write(IV.getIV());
        }

        File SaltFile = new File(TEMP_FILE_PATH + "salt.txt");
        try (FileOutputStream Saltoutput = new FileOutputStream(SaltFile)) {
            Saltoutput.write(salt);
        }

        return ZipFileService.prepareZipFile(encryptedFile, IVFile, SaltFile);
    }

    public File encryptDataWithKeyPair(File file, File key) throws Exception {

        String sessionID = UUID.randomUUID().toString();
        File tempFile = new File(TEMP_FILE_PATH + sessionID);
//          Create the new file directory along with it.
        tempFile.mkdirs();

        SecretKey aesKey = Encrypt.SecKey();
        GCMParameterSpec IV = Encrypt.genIV();

        File encryptedFile = new File(TEMP_FILE_PATH + "encrypted.enc");
        Encrypt.FileEncrypt(aesKey, IV, file, encryptedFile);

        PublicKey publicKey = KeyPairService.loadPublicKey(key);

        String encryptedKeyIV = RSAUtils.encrypt(aesKey, IV, publicKey);
        File encKeyIVFile = new File(TEMP_FILE_PATH + "encrypted_Key_IV.txt");
        RSAUtils.saveEncryptedKeyIV(encryptedKeyIV, encKeyIVFile);

        return ZipFileService.prepareZipFile(encryptedFile, encKeyIVFile);
    }
}
