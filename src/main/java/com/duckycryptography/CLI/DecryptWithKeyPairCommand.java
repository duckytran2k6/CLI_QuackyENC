package com.duckycryptography.CLI;

import picocli.CommandLine;

@CommandLine.Command (
        name = "-d-kp",
        description = "Decrypt a file using a generated key pair."
)

public class DecryptWithKeyPairCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Decrypting with key pair!");
    }
}
