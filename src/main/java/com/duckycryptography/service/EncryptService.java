package com.duckycryptography.service;

import com.duckycryptography.core.Encrypt;
import com.duckycryptography.core.PasswordDeriveUtils;
import com.duckycryptography.core.RSAUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.io.File;
import java.io.FileOutputStream;
import java.security.PublicKey;
import java.util.UUID;

@Service
public class EncryptService {
    //    Using the .getProperty() method to retrieve the value of a system property (ex. Window/Mac/Linux) then use the java property to create a temp folder directory.
    private final String TEMP_FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;

    public File encryptDataWithPassword(MultipartFile file, String password) throws Exception {

        String sessionID = UUID.randomUUID().toString();
        File tempFile = new File(TEMP_FILE_PATH + sessionID);
//          Delete old folders (directory) to clean up for the new file to be created.
        FileSystemUtils.deleteRecursively(tempFile);
//          Create the new file directory along with it.
        tempFile.mkdirs();

        File inputFile = File.createTempFile("inputFile", "tmp", tempFile);
        file.transferTo(inputFile);

        byte[] salt = Encrypt.generateSalt();
        SecretKey aesKey = PasswordDeriveUtils.derivedFromPassword(password, salt);
        GCMParameterSpec IV = Encrypt.genIV();

        File encryptedFile = new File(TEMP_FILE_PATH + "encrypted.enc");
        Encrypt.FileEncrypt(aesKey, IV, inputFile, encryptedFile);

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

    public File encryptDataWithKeyPair(MultipartFile file, MultipartFile key) throws Exception {

        String sessionID = UUID.randomUUID().toString();
        File tempFile = new File(TEMP_FILE_PATH + sessionID);
//          Delete old folders (directory) to clean up for the new file to be created.
        FileSystemUtils.deleteRecursively(tempFile);
//          Create the new file directory along with it.
        tempFile.mkdirs();

        File inputFile = File.createTempFile("inputFile", "tmp", tempFile);
        file.transferTo(inputFile);

        SecretKey aesKey = Encrypt.SecKey();
        GCMParameterSpec IV = Encrypt.genIV();

        File encryptedFile = new File(TEMP_FILE_PATH + "encrypted.enc");
        Encrypt.FileEncrypt(aesKey, IV, inputFile, encryptedFile);

        File pubKeyFile = LoadFilesService.loadFiles(key, "public.key");
        PublicKey publicKey = KeyPairService.loadPublicKey(pubKeyFile);

        String encryptedKeyIV = RSAUtils.encrypt(aesKey, IV, publicKey);
        File encKeyIVFile = new File(TEMP_FILE_PATH + "encrypted_Key_IV.txt");
        RSAUtils.saveEncryptedKeyIV(encryptedKeyIV, encKeyIVFile);

        return ZipFileService.prepareZipFile(encryptedFile, encKeyIVFile);
    }
}
