package com.duckycryptography.CLI;

import com.duckycryptography.service.DecryptService;
import com.duckycryptography.service.EncryptService;
import picocli.CommandLine;

import java.io.File;

@CommandLine.Command (
        name = "-d-kp",
        description = "Decrypt a file using a generated key pair."
)

public class DecryptWithKeyPairCommand implements Runnable {


    @CommandLine.Parameters(arity = "3", description = "Please upload the encrypted file, encrypted key IV, and the private key!")
    private File[] files;

    @Override
    public void run() {

        if (files == null || files.length != 3) {
            System.err.println("Please upload the files as specified above!");
            return;
        }
        File inputFile = files[0];
        File keyIVFile = files[1];
        File privateKeyFile = files[2];

        System.out.println("inputFile: " + inputFile.getAbsolutePath());
        System.out.println("keyIVFile: " + keyIVFile.getAbsolutePath());
        System.out.println("privateKeyFile: " + privateKeyFile.getAbsolutePath());

        DecryptService dKP = new DecryptService();
        try {
            dKP.decryptWithKeyPair(inputFile, keyIVFile, privateKeyFile);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
