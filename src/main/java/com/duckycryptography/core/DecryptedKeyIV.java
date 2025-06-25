package com.duckycryptography.core;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class DecryptedKeyIV {
    public SecretKey key;
    public GCMParameterSpec IV;

    public DecryptedKeyIV(SecretKey key, GCMParameterSpec IV) {
        this.key = key;
        this.IV = IV;
    }

    public SecretKey getSecKey() {
        return key;
    }

    public GCMParameterSpec getIV() {
        return IV;
    }

}
