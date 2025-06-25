package com.duckycryptography.service;

import com.duckycryptography.core.Decrypt;
import com.duckycryptography.core.DecryptedKeyIV;
import com.duckycryptography.core.PasswordDeriveUtils;
import com.duckycryptography.core.RSAUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.io.File;
import java.nio.file.Files;
import java.security.PrivateKey;

import static com.duckycryptography.service.LoadFilesService.loadFiles;


@Service
public class DecryptService {
    private final String TEMP_FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;

    public File decryptWithPassword(MultipartFile encryptedFile, MultipartFile IV,MultipartFile salt, String password) throws Exception {
        File encrFile = loadFiles(encryptedFile, "encrypted.enc");
        File IVFile = loadFiles(IV, "IV.txt");
        File saltFile = loadFiles(salt, "salt.txt");

        byte[] saltToByte = Files.readAllBytes(saltFile.toPath());
        byte[] IVToByte = Files.readAllBytes(IVFile.toPath());

        GCMParameterSpec ivSpec = new GCMParameterSpec(128, IVToByte);

        SecretKey aesKey = PasswordDeriveUtils.derivedFromPassword(password, saltToByte);

        File decryptedFile = new File(TEMP_FILE_PATH + "decrypted.txt");
        Decrypt.FileDecrypt(aesKey, ivSpec, encrFile, decryptedFile);

        return decryptedFile;
    }

    public File decryptWithKeyPair(MultipartFile encryptedFile, MultipartFile encryptedKeyIV, MultipartFile privateKeyFile) throws Exception {
        File encrFile = loadFiles(encryptedFile, "encrypted.enc");
        File encrKeyIV = loadFiles(encryptedKeyIV, "encrypted.key");
        File privKeyFile = loadFiles(privateKeyFile, "private.key");

        String encryptedKeyIVString = RSAUtils.loadEncryptedKeyIV(encrKeyIV);

        PrivateKey privKey = KeyPairService.loadPrivateKey(privKeyFile);

        DecryptedKeyIV decryptedKeyIV = RSAUtils.decrypt(encryptedKeyIVString, privKey);

        File decryptedFile = new File(TEMP_FILE_PATH + "decrypted.txt");
        Decrypt.FileDecrypt(decryptedKeyIV.getSecKey(), decryptedKeyIV.getIV(), encrFile, decryptedFile);

        return decryptedFile;

    }

}
