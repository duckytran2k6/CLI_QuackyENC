package com.duckycryptography.service;

import com.duckycryptography.core.RSAUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Service
public class KeyPairService {
    private final String TEMP_FILE_PATH = System.getProperty("java.io.tmpdir") + File.separator;

    public void generateKeyPair() throws Exception{
        KeyPair keyPair = RSAUtils.generateKeyPairs();
        File publicKeyFile = new File(TEMP_FILE_PATH + "public.key");
        File privateKeyFile = new File(TEMP_FILE_PATH + "private.key");
        saveKeyPair(keyPair, publicKeyFile, privateKeyFile);
    }

    public static PublicKey loadPublicKey(File publicPath) throws Exception {
        byte[] publicByte = Files.readAllBytes(publicPath.toPath());
        X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(publicByte);
        return KeyFactory.getInstance("RSA").generatePublic(publicSpec);
    }

    public static PrivateKey loadPrivateKey(File privatePath) throws Exception {
        byte[] privateByte = Files.readAllBytes(privatePath.toPath());
        PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(privateByte);
        return KeyFactory.getInstance("RSA").generatePrivate(privateSpec);
    }

    public static void saveKeyPair(KeyPair keyPair, File publicPath, File privatePath) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(publicPath)) {
            fos.write(keyPair.getPublic().getEncoded());
        }

        try (FileOutputStream fos = new FileOutputStream(privatePath)) {
            fos.write(keyPair.getPrivate().getEncoded());
        }
    }
}
