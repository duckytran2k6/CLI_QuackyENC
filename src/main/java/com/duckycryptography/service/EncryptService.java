package com.duckycryptography.service;

import com.duckycryptography.core.Encrypt;
import com.duckycryptography.core.PasswordDeriveUtils;
import com.duckycryptography.core.RSAUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.io.File;
import java.io.FileOutputStream;
import java.security.PublicKey;
import java.util.Objects;
import java.util.UUID;

public class EncryptService {
    //    Using the .getProperty() method to retrieve the value of a system property (ex. Window/Mac/Linux) then use the java property to create a temp folder directory.
    private final String TEMP_FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;

    public File encryptDataWithPassword(File file, String password) throws Exception {

        String sessionID = UUID.randomUUID().toString();
        File sessionDir = new File(TEMP_FILE_PATH + sessionID + File.separator);
//          Create the new file directory along with it.
        sessionDir.mkdirs();

        File encryptedFile = new File(sessionDir,"encrypted.enc");
        File ivFile = new File(sessionDir,"IV.txt");
        File saltFile = new File(sessionDir,"salt.txt");

        try {
            byte[] salt = Encrypt.generateSalt();
            SecretKey aesKey = PasswordDeriveUtils.derivedFromPassword(password, salt);
            GCMParameterSpec IV = Encrypt.genIV();

            encryptedFile = new File(sessionDir,"encrypted.enc");
            Encrypt.FileEncrypt(aesKey, IV, file, encryptedFile);

            ivFile = new File(sessionDir,"IV.txt");
            try (FileOutputStream IVoutput = new FileOutputStream(ivFile)) {
                IVoutput.write(IV.getIV());
            }

            saltFile = new File(sessionDir,"salt.txt");
            try (FileOutputStream Saltoutput = new FileOutputStream(saltFile)) {
                Saltoutput.write(salt);
            }

            File zipFile = ZipFileService.prepareZipFile(encryptedFile, ivFile, saltFile);

            System.out.println("The encrypted zip file is saved to : " + zipFile.getAbsolutePath());

            return zipFile;
        } catch (Exception e) {
            System.err.println("Encryption failed: " + e.getMessage());
            return null;
        } finally {
            ZipFileService.postZipFileCleanUp(encryptedFile);
            ZipFileService.postZipFileCleanUp(ivFile);
            ZipFileService.postZipFileCleanUp(saltFile);
            if (sessionDir.exists() && Objects.requireNonNull(sessionDir.listFiles()).length == 0) {
                sessionDir.delete();
            }
        }
    }

    public File encryptDataWithKeyPair(File file, File key) throws Exception {

        String sessionID = UUID.randomUUID().toString();
        File sessionDir = new File(TEMP_FILE_PATH + sessionID + File.separator);
//          Create the new file directory along with it.
        sessionDir.mkdirs();

        File encryptedFile = new File(sessionDir, "encrypted.enc");
        File encKeyIVFile = new File(sessionDir, "encrypted_Key_IV.txt");

        try {
            SecretKey aesKey = Encrypt.SecKey();
            GCMParameterSpec IV = Encrypt.genIV();

            encryptedFile = new File(sessionDir, "encrypted.enc");
            Encrypt.FileEncrypt(aesKey, IV, file, encryptedFile);

            PublicKey publicKey = KeyPairService.loadPublicKey(key);

            String encryptedKeyIV = RSAUtils.encrypt(aesKey, IV, publicKey);
            encKeyIVFile = new File(sessionDir, "encrypted_Key_IV.txt");
            RSAUtils.saveEncryptedKeyIV(encryptedKeyIV, encKeyIVFile);

            File zipFile = ZipFileService.prepareZipFile(encryptedFile, encKeyIVFile);

            ZipFileService.postZipFileCleanUp(encryptedFile);
            ZipFileService.postZipFileCleanUp(encKeyIVFile);

            System.out.println("The encrypted zip file is saved to : " + zipFile.getAbsolutePath());

            return zipFile;
        } catch (Exception e) {
            System.err.println("Encryption failed: " + e.getMessage());
            return null;
        } finally {
            ZipFileService.postZipFileCleanUp(encryptedFile);
            ZipFileService.postZipFileCleanUp(encKeyIVFile);
            if (sessionDir.exists() && Objects.requireNonNull(sessionDir.listFiles()).length == 0) {
                sessionDir.delete();
            }
        }
    }
}
