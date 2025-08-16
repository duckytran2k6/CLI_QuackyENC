package com.duckycryptography.cli;

import com.duckycryptography.service.DecryptService;
import com.duckycryptography.service.FilesService;
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
            List<File> files = FilesService.selectMultipleFiles("Select the files you want to be decrypted!");

            if (!ValidityCheckerService.checkListLimit(files)) {return;}

            File keyFile = null;
            File ivFile = null;
            File privateKeyFile = null;
            for (File file : files) {
                if (ValidityCheckerService.checkFileExists(file, "encrypted_Key.txt")) {
                    keyFile = file;
                } else if (ValidityCheckerService.checkFileExists(file, "private.key")) {
                    privateKeyFile = file;
                } else if (ValidityCheckerService.checkFileExists(file, "iv.txt")) {
                    ivFile = file;
                }
            }

            if (!ValidityCheckerService.checkFile(keyFile, "keyFile") || !ValidityCheckerService.checkFile(privateKeyFile, "privateKeyFile")) {return;}

            files.remove(keyFile);
            files.remove(privateKeyFile);
            files.remove(ivFile);

            DecryptService dKP = new DecryptService();
            dKP.decryptWithKeyPair(files, keyFile, ivFile, privateKeyFile);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
