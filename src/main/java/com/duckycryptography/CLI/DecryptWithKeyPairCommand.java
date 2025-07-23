package com.duckycryptography.CLI;

import com.duckycryptography.service.DecryptService;
import com.duckycryptography.service.FilesSelectService;
import com.duckycryptography.service.ValidityCheckerService;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

@CommandLine.Command (
        name = "-kp",
        description = "Decryption using a generated key pair!"
)

public class DecryptWithKeyPairCommand implements Runnable {

    @Override
    public void run() {
        try {
            List<File> files = FilesSelectService.selectMultipleFiles("Select the files you want to be decrypted!");

            if (!ValidityCheckerService.checkListLimit(files)) {return;}

            File keyivFile = null;
            File privateKeyFile = null;
            for (File file : files) {
                if (ValidityCheckerService.checkFileExists(file, "encrypted_Key_IV.txt")) {
                    keyivFile = file;
                } else if (ValidityCheckerService.checkFileExists(file, "private.key")) {
                    privateKeyFile = file;
                }
            }

            if (!ValidityCheckerService.checkFile(keyivFile, "keyIVFile") || !ValidityCheckerService.checkFile(privateKeyFile, "privateKeyFile")) {return;}

            files.remove(keyivFile);
            files.remove(privateKeyFile);

            DecryptService dKP = new DecryptService();
            dKP.decryptWithKeyPair(files, keyivFile, privateKeyFile);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
