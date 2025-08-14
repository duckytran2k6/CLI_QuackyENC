package com.duckycryptography.cli;

import picocli.CommandLine;

@CommandLine.Command (
        name = "-e",
        subcommands = {
                EncryptWithPasswordCommand.class,
                EncryptWithKeyPairCommand.class
        },
        description = "Encrypt using either password or key pair method."
)

public class EncryptCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Please select one of these following options for more information:\n -pass: encrypt using password-based method.\n -kp: encrypt using key pair-based method.");
    }

}
