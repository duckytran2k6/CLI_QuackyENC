package com.duckycryptography.CLI;
import com.duckycryptography.service.EncryptService;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

@CommandLine.Command (
        name = "-e-kp",
        description = "Encrypt a file using a generated key pair."
)

public class EncryptWithKeyPairCommand implements Runnable {

    @CommandLine.Parameters(arity = "1..", paramLabel = "FILES", description = "Please upload the files to be encrypted!")
    private List<File> inputFiles;

    @CommandLine.Option(names = {"-pk", "--publicKey"}, required = true, description = "Upload the valid public key!")
    private File publicKeyFile;

    @Override
    public void run() {
        if (inputFiles.isEmpty()) {
            System.err.println("Please upload the files as specified above!");
            return;
        }

        for (File inputFile : inputFiles) {
            if (!(inputFile == null || !inputFile.exists())) {
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
