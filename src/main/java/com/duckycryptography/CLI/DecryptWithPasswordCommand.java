package com.duckycryptography.CLI;

import picocli.CommandLine;

@CommandLine.Command (
        name = "Decrypt-password"
)

public class DecryptWithPasswordCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Decrypting with password!");
    }
}
