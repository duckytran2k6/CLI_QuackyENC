package com.duckycryptography.CLI;

import picocli.CommandLine;

@CommandLine.Command(
        name = "encrypt"
)

public class EncryptCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Encrypting");
    }
}
