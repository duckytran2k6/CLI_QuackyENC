package com.duckycryptography.CLI;

import com.duckycryptography.service.DecryptService;
import com.duckycryptography.service.ValidityCheckerService;
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
        if (!ValidityCheckerService.checkListLimit(files)) {
            return;
        }

        if (!ValidityCheckerService.checkFile(keyIVFile, "keyIVFile") || !ValidityCheckerService.checkFile(privateKeyFile, "privateKeyFile")) {
            return;
        }

        for (int i = 0; i < files.size(); i++) {
            File inputFile = files.get(i);
            if (ValidityCheckerService.checkFile(inputFile, "File #" + (i + 1) + " (" + (inputFile != null ? inputFile.getName() : "unknown") + ")")) {
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
