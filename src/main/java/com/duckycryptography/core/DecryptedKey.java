package com.duckycryptography.core;
import javax.crypto.SecretKey;

public class DecryptedKey {
    public SecretKey key;

    public DecryptedKey(SecretKey key) {
        this.key = key;
    }

    public SecretKey getSecKey() {
        return key;
    }

}
