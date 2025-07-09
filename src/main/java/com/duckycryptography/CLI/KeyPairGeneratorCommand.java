package com.duckycryptography.CLI;

import picocli.CommandLine;

@CommandLine.Command (
        name = "-kpg",
        description = "Generate a key pair."
)

public class KeyPairGeneratorCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Generating key pairs...");
    }
}
