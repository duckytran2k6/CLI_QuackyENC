package com.duckycryptography.CLI;

import picocli.CommandLine;

@CommandLine.Command (
        name = "-e",
        subcommands = {
                EncryptWithPasswordCommand.class,
                EncryptWithKeyPairCommand.class
        },
        description = "Encrypt mode using either password or key pair method."
)

public class EncryptCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Please use the following commands for more information:\n -e -pass: encrypt using password.\n -e -kp: encrypt using key pair.");
    }

}
