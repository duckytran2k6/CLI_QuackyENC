package com.duckycryptography.CLI;

import com.duckycryptography.service.DecryptService;
import com.duckycryptography.service.FileValidityService;
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
        if (FileValidityService.checkListLimit(files)) {
            return;
        }

        if (!FileValidityService.checkFile(keyIVFile, "keyIVFile") || !FileValidityService.checkFile(privateKeyFile, "privateKeyFile")) {
            return;
        }

        for (File inputFile : files) {
            if (FileValidityService.checkFile(inputFile, "inputFile")) {
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
