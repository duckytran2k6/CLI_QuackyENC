package com.duckycryptography.CLI;

import picocli.CommandLine;

@CommandLine.Command (
        name = "-e",
        mixinStandardHelpOptions = true,
        subcommands = {
                EncryptWithPasswordCommand.class,
                EncryptWithKeyPairCommand.class
        },
        description = "Encrypt mode using either password or key pair method."
)

public class EncryptCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Please choose either 'encrypt-password' or 'encrypt-key-pair'.");
    }
}
