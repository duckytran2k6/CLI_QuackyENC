package com.duckycryptography.CLI;
import com.duckycryptography.service.EncryptService;
import picocli.CommandLine;

import java.io.File;

@CommandLine.Command (
        name = "-e-kp",
        description = "Encrypt a file using a generated key pair."
)

public class EncryptWithKeyPairCommand implements Runnable {

    @CommandLine.Parameters(arity = "2", description = "Please upload the plain text file and the public key!")
    private File[] files;

    @Override
    public void run() {
        if (files == null || files.length < 2) {
            System.err.println("Please upload the files as specified above!");
            return;
        }
        File inputFile = files[0];
        File publicKeyFile = files[1];

        System.out.println("inputFile: " + inputFile.getAbsolutePath());
        System.out.println("publicKeyFile: " + publicKeyFile.getAbsolutePath());

        EncryptService eKP = new EncryptService();
        try {
            eKP.encryptDataWithKeyPair(inputFile, publicKeyFile);
        } catch (Exception e) {
           System.err.println(e.getMessage());
        }
    }
}
