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
        System.out.println("Please use the following commands for more information:\n -d -pass: decrypt using password.\n -d -kp: decrypt using key pair.");
    }
}