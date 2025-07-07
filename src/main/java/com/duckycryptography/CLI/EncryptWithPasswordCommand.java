package com.duckycryptography.CLI;

import picocli.CommandLine;

@CommandLine.Command (
        name = "encrypt-password",
        description = "Encrypt a file using a provided password."
)

public class EncryptWithPasswordCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Encrypting with password!");
    }
}
