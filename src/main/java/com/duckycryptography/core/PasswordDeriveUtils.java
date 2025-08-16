package com.duckycryptography.core;

import com.duckycryptography.service.CleanUpService;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;

public class PasswordDeriveUtils {

    public static SecretKey derivedFromPassword(char[] password, byte[] salt) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
        SecretKey key = factory.generateSecret(spec);
        CleanUpService.authCleanUp(salt);
        return new SecretKeySpec(key.getEncoded(), "AES");
    }
}
