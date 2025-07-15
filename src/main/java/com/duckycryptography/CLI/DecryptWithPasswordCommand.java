package com.duckycryptography.CLI;

import com.duckycryptography.service.DecryptService;
import com.duckycryptography.service.PasswordChecker;
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
        if (files.isEmpty()) {
            System.err.println("Please provide a file to encrypt!");
            return;
        }

        if (IV == null || !IV.exists()) {
            System.err.println("The IV file is empty/does not exist!");
            return;
        }

        if (salt == null || !salt.exists()) {
            System.err.println("The salt file is empty/does not exist!");
            return;
        }

        if (PasswordChecker.validPassword(password)) {
            System.err.println("Please provide a valid password!");
            return;
        }

        for (File inputFile : files) {
            if (!(inputFile == null || !inputFile.exists())) {
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
