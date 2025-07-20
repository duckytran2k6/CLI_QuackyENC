package com.duckycryptography.CLI;
import com.duckycryptography.service.EncryptService;
import com.duckycryptography.service.FilesSelectService;
import com.duckycryptography.service.ValidityCheckerService;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

@CommandLine.Command (
        name = "-kp",
        description = "Encryption using a generated key pair!"
)

public class EncryptWithKeyPairCommand implements Runnable {

    @CommandLine.Option(names = {"-pubK", "--publicKey"}, required = true, description = "Upload the valid public key!")
    private File publicKeyFile;

    @Override
    public void run() {
        List<File> files = FilesSelectService.selectMultipleFiles("Select the files you want to be encrypted!");

        if (!ValidityCheckerService.checkListLimit(files)) {
            return;
        }

        if (!ValidityCheckerService.checkFile(publicKeyFile, "publicKey")) {
            System.out.println("Opening the file explorer, please wait...!");
            publicKeyFile = FilesSelectService.selectSingleFile("Select the receiver's public key file!");
            if (!ValidityCheckerService.checkFile(publicKeyFile, "publicKey")) {
                return;
            }
        }

        EncryptService eKP = new EncryptService();
        try {
            eKP.encryptDataWithKeyPair(files, publicKeyFile);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
