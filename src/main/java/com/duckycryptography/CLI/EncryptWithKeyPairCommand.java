package com.duckycryptography.CLI;
import com.duckycryptography.service.EncryptService;
import com.duckycryptography.service.ValidityCheckerService;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

@CommandLine.Command (
        name = "-e-kp",
        description = "Encrypt a file using a generated key pair."
)

public class EncryptWithKeyPairCommand implements Runnable {

    @CommandLine.Parameters(arity = "1..", paramLabel = "FILES", description = "Please upload the files to be encrypted!")
    private List<File> files;

    @CommandLine.Option(names = {"-pubK", "--publicKey"}, required = true, description = "Upload the valid public key!")
    private File publicKeyFile;

    @Override
    public void run() {
        if (!ValidityCheckerService.checkListLimit(files)) {
            return;
        }

        if (!ValidityCheckerService.checkFile(publicKeyFile, "publicKey")) {
            return;
        }

        for (int i = 0; i < files.size(); i++) {
            File inputFile = files.get(i);
            if (ValidityCheckerService.checkFile(inputFile, "File #" + (i + 1) + " (" + (inputFile != null ? inputFile.getName() : "unknown") + ")")) {
                EncryptService eKP = new EncryptService();
                try {
                    eKP.encryptDataWithKeyPair(inputFile, publicKeyFile);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}
