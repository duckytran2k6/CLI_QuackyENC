package com.duckycryptography.CLI;

import picocli.CommandLine;

@CommandLine.Command (
        name = "Decrypt"
)

public class DecryptCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Decrypting");
    }
}
