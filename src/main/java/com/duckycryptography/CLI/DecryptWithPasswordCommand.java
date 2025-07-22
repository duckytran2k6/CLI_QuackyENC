package com.duckycryptography.CLI;

import com.duckycryptography.service.DecryptService;
import com.duckycryptography.service.FilesSelectService;
import com.duckycryptography.service.ValidityCheckerService;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

@CommandLine.Command (
        name = "-pass",
        description = "Decryption using a provided password!"
)

public class DecryptWithPasswordCommand implements Runnable {

    @CommandLine.Option(names = {"-pw", "password"}, required = true, description = "Please enter the correct password!")
    private String password;

    @Override
    public void run() {
        try {
            List<File> files = FilesSelectService.selectMultipleFiles("Select the files you want to be decrypted!");

            if (!ValidityCheckerService.checkListLimit(files)) {return;}

            File iv = null;
            File salt = null;
            for (File file : files) {
                if (ValidityCheckerService.checkFileExists(file, "IV.txt")) {
                    iv = file;
                } else if (ValidityCheckerService.checkFileExists(file, "salt.txt")) {
                    salt = file;
                }
            }

            if (!ValidityCheckerService.checkFile(iv, "IV") || !ValidityCheckerService.checkFile(salt, "salt")) {return;}

            if (!ValidityCheckerService.validPassword(password)) {
                System.err.println("Please provide a valid password!");
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
