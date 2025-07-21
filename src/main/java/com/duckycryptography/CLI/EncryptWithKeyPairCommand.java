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

    @Override
    public void run() {
        try {
            List<File> files = FilesSelectService.selectMultipleFiles("Select the files you want to be encrypted!");

            if (!ValidityCheckerService.checkListLimit(files)) {
                return;
            }

            File publicKeyFile = null;
            for (File file : files) {
                if (ValidityCheckerService.checkFileExists(file, "public.key")) {
                    publicKeyFile = file;
                    break;
                }
            }

            if (!ValidityCheckerService.checkFile(publicKeyFile, "publicKey")) {
                return;
            }

            files.remove(publicKeyFile);

            EncryptService eKP = new EncryptService();
                eKP.encryptDataWithKeyPair(files, publicKeyFile);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
