package com.duckycryptography.CLI;

import com.duckycryptography.service.EncryptService;
import com.duckycryptography.service.PasswordChecker;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

@CommandLine.Command (
        name = "-e-p",
        description = "Encrypt a file using a provided password."
)

public class EncryptWithPasswordCommand implements Runnable {
    @CommandLine.Parameters(arity = "1..", paramLabel = "FILES", description = "Please upload the file to be encrypted!")
    private List<File> inputFiles;

    @CommandLine.Option(names = {"-pw", "--password"}, required = true, description = "Please enter a password with a minimum of 8 characters!")
    private String password;

    @Override
    public void run() {
        if (inputFiles.isEmpty()) {
            System.err.println("The provided file(s) is empty/does not exist!");
            return;
        }

        if (PasswordChecker.validPassword(password)) {
            System.err.println("Please provide a valid password!");
            return;
        }


        for (File inputFile : inputFiles) {
            if (!(inputFile == null || !inputFile.exists())) {
                EncryptService eP = new EncryptService();
                try {
                    eP.encryptDataWithPassword((File) inputFiles, password);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}
