package com.duckycryptography.cli;

import picocli.CommandLine;

@CommandLine.Command (
        name = "-d",
        mixinStandardHelpOptions = true,
        subcommands = {
                DecryptWithPasswordCommand.class,
                DecryptWithKeyPairCommand.class
        },
        description = "Decrypt using either password or key pair method."
)

public class DecryptCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Please select one of these following options for more information:\n -pass: decrypt using password-based method.\n -kp: decrypt using key pair-based method.");
    }
}