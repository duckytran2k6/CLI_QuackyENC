package com.duckycryptography.CLI;

import picocli.CommandLine;

@CommandLine.Command (
        name = "Decrypt-password",
        description = "Decrypt a file using a provided password."
)

public class DecryptWithPasswordCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Decrypting with password!");
    }
}
