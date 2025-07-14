package com.duckycryptography.CLI;

import com.duckycryptography.service.EncryptService;
import com.duckycryptography.service.PasswordChecker;
import picocli.CommandLine;

import java.io.File;

@CommandLine.Command (
        name = "-e-p",
        description = "Encrypt a file using a provided password."
)

public class EncryptWithPasswordCommand implements Runnable {
    @CommandLine.Parameters(arity = "1", description = "Please upload the file to be encrypted!")
    private File[] inputFileForEncryption;

    @CommandLine.Option(names = {"-pw", "--password"}, description = "Please enter a password with a minimum of 8 characters!")
    private String password;

    @Override
    public void run() {
        if (inputFileForEncryption == null || inputFileForEncryption.length < 1) {
            System.err.println("The provided file is empty/does not exist!");
            return;
        }

        if (PasswordChecker.validPassword(password)) {
            System.err.println("Please provide a valid password!");
            return;
        }

        File fileToEncrypt = inputFileForEncryption[0];

        EncryptService eP = new EncryptService();
        try {
            eP.encryptDataWithPassword(fileToEncrypt, password);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
