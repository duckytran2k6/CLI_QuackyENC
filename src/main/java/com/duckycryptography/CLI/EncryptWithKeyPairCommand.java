package com.duckycryptography.CLI;

import picocli.CommandLine;

@CommandLine.Command (
        name = "encrypt-key-pair",
        description = "Encrypt a file using a generated key pair."
)

public class EncryptWithKeyPairCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Encrypting with key pair!");
    }
}
