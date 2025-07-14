package com.duckycryptography.CLI;

import com.duckycryptography.service.KeyPairService;
import picocli.CommandLine;

@CommandLine.Command (
        name = "-kpg",
        description = "Generate a key pair."
)

public class KeyPairGeneratorCommand implements Runnable {
    @Override
    public void run() {
        KeyPairService kPG = new KeyPairService();
        try {
            kPG.generateKeyPair();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
