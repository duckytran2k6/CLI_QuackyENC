package com.duckycryptography.cli;

import com.duckycryptography.service.DecryptService;
import com.duckycryptography.service.FilesService;
import com.duckycryptography.service.ValidityCheckerService;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

@CommandLine.Command (
        name = "-pass",
        description = "Decryption using a provided password!"
)

public class DecryptWithPasswordCommand implements Runnable {

    @CommandLine.Option(names = {"-pw", "password"}, interactive = true, arity = "0..1", description = "Please enter the correct password!")
    private char[] password;

    @Override
    public void run() {
        try {
            List<File> files = FilesService.selectMultipleFiles("Select the files you want to be decrypted!");

            if (!ValidityCheckerService.checkListLimit(files)) {return;}

            File iv = null;
            File salt = null;
            for (File file : files) {
                if (ValidityCheckerService.checkFileExists(file, "iv.txt")) {
                    iv = file;
                } else if (ValidityCheckerService.checkFileExists(file, "salt.txt")) {
                    salt = file;
                }
            }

            if (!ValidityCheckerService.checkFile(iv, "IV") || !ValidityCheckerService.checkFile(salt, "salt")) {return;}

            if (ValidityCheckerService.validPassword(password)) {
                return;
            }

            files.remove(iv);
            files.remove(salt);

            DecryptService dP = new DecryptService();
            dP.decryptWithPassword(files, iv, salt, password);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
