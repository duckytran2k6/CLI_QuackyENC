package com.duckycryptography.CLI;

import picocli.CommandLine;

@CommandLine.Command (
        name = "encrypt-password"
)

public class EncryptWithPasswordCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Encrypting with password!");
    }
}
