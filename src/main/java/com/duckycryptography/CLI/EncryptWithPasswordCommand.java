package com.duckycryptography.CLI;

import com.duckycryptography.service.EncryptService;
import com.duckycryptography.service.ValidityCheckerService;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

@CommandLine.Command (
        name = "-e-pass",
        description = "Encrypt a file using a provided password."
)

public class EncryptWithPasswordCommand implements Runnable {
    @CommandLine.Parameters(arity = "1..", paramLabel = "FILES", description = "Please upload the file to be encrypted!")
    private List<File> files;

    @CommandLine.Option(names = {"-pw", "--password"}, required = true, description = "Please enter a password with a minimum of 8 characters!")
    private String password;

    @Override
    public void run() {
        if (!ValidityCheckerService.checkListLimit(files)) {
            return;
        }

        if (!ValidityCheckerService.validPassword(password)) {
            System.err.println("Please provide a valid password!");
            return;
        }

        for (int i = 0; i < files.size(); i++) {
            File inputFile = files.get(i);
            if (ValidityCheckerService.checkFile(inputFile, "File #" + (i + 1) + " (" + (inputFile != null ? inputFile.getName() : "unknown") + ")")) {
                EncryptService eP = new EncryptService();
                try {
                    eP.encryptDataWithPassword(inputFile, password);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}
