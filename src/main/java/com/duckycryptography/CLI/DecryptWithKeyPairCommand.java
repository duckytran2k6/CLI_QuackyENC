package com.duckycryptography.CLI;

import com.duckycryptography.service.DecryptService;
import com.duckycryptography.service.EncryptService;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

@CommandLine.Command (
        name = "-d-kp",
        description = "Decrypt a file using a generated key pair."
)

public class DecryptWithKeyPairCommand implements Runnable {


    @CommandLine.Parameters(arity = "1..", paramLabel = "FILES", description = "Upload the encrypted files to be decrypt!")
    private List<File> files;

    @CommandLine.Option(names = {"-kIV", "--keyIV"}, required = true, description = "Upload the encrypted file containing the key and IV!")
    private File keyIVFile;

    @CommandLine.Option(names = {"-priK","--privateKey"}, required = true, description = "Upload the valid private key!")
    private File privateKeyFile;

    @Override
    public void run() {

        if (files.isEmpty()) {
            System.err.println("Please upload the files as specified above!");
            return;
        }

        if (keyIVFile == null || !keyIVFile.exists()) {
            System.err.println("The key IV file is empty/does not exist!");
            return;
        }

        if (privateKeyFile == null || !privateKeyFile.exists()) {
            System.err.println("The private key file is empty/does not exist!");
            return;
        }

        for (File inputFile : files) {
            if (!(inputFile == null || !inputFile.exists())) {
                DecryptService dKP = new DecryptService();
                try {
                    dKP.decryptWithKeyPair(inputFile, keyIVFile, privateKeyFile);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}
