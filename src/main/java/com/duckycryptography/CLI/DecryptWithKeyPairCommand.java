package com.duckycryptography.CLI;

import picocli.CommandLine;

@CommandLine.Command (
        name = "Decrypt-key-pair",
        description = "Decrypt a file using a generated key pair."
)

public class DecryptWithKeyPairCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Decrypting with key pair!");
    }
}
