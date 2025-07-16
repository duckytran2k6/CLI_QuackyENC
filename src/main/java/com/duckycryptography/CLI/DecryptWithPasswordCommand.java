package com.duckycryptography.CLI;

import com.duckycryptography.service.DecryptService;
import com.duckycryptography.service.ValidityCheckerService;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

@CommandLine.Command (
        name = "-d-pass",
        description = "Decrypt a file using a provided password."
)

public class DecryptWithPasswordCommand implements Runnable {
    @CommandLine.Parameters(arity = "3", description = "Please upload the encrypted file, IV, and salt!")
    private List<File> files;

    @CommandLine.Option(names = {"-IV"}, required = true, description = "Upload the encrypted IV file!")
    private File IV;

    @CommandLine.Option(names = {"-s", "--salt"}, required = true, description = "Upload the encrypted salt file!")
    private File salt;

    @CommandLine.Option(names = {"-pw", "password"}, required = true, description = "Please enter the correct password!")
    private String password;

    @Override
    public void run() {
        if (!ValidityCheckerService.checkListLimit(files)) {
            return;
        }

        if (!ValidityCheckerService.checkFile(IV, "IV") || !ValidityCheckerService.checkFile(salt, "salt")) {
            return;
        }

        if (!ValidityCheckerService.validPassword(password)) {
            System.err.println("Please provide a valid password!");
            return;
        }

        for (File inputFile : files) {
            if (ValidityCheckerService.checkFile(inputFile, "inputFile")) {
                DecryptService dP = new DecryptService();
                try {
                    dP.decryptWithPassword(inputFile, IV, salt, password);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}
