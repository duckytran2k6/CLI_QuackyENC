package com.duckycryptography.CLI;

import com.duckycryptography.service.DecryptService;
import com.duckycryptography.service.PasswordChecker;
import picocli.CommandLine;

import java.io.File;

@CommandLine.Command (
        name = "-d-p",
        description = "Decrypt a file using a provided password."
)

public class DecryptWithPasswordCommand implements Runnable {
    @CommandLine.Parameters(arity = "3", description = "Please upload the encrypted file, IV, and salt!")
    private File[] inputFileForDecryption;

    @CommandLine.Option(names = {"-pw", "password"}, required = true, description = "Please enter the correct password!")
    private String password;

    @Override
    public void run() {
        if (inputFileForDecryption == null || inputFileForDecryption.length < 3) {
            System.err.println("Please provide a file to encrypt!");
            return;
        }

        if (PasswordChecker.validPassword(password)) {
            System.err.println("Please provide a valid password!");
            return;
        }

        File fileToDecrypt = inputFileForDecryption[0];
        File IV = inputFileForDecryption[1];
        File salt = inputFileForDecryption[2];

        DecryptService dP = new DecryptService();
        try {
            dP.decryptWithPassword(fileToDecrypt, IV, salt, password);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
