package com.duckycryptography.CLI;

import picocli.CommandLine;

@CommandLine.Command (
        name = "-d",
        mixinStandardHelpOptions = true,
        subcommands = {
                DecryptWithPasswordCommand.class,
                DecryptWithKeyPairCommand.class
        },
        description = "Encrypt mode using either password or key pair method."
)

public class DecryptCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Please choose either 'decrypt-password' or 'decrypt-key-pair'.");
    }
}