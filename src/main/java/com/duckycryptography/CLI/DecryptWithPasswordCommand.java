package com.duckycryptography.CLI;

import com.duckycryptography.service.DecryptService;
import picocli.CommandLine;

import java.io.File;

@CommandLine.Command (
        name = "-d-p",
        description = "Decrypt a file using a provided password."
)

public class DecryptWithPasswordCommand implements Runnable {
    @CommandLine.Parameters(arity = "3", description = "Please upload the encrypted file, IV, and salt!")
    private File[] inputFileForDecryption;

    @CommandLine.Option(names = {"-pw", "password"}, description = "Please enter the correct password!")
    private char[] inputPasswordForDecryption;

    @Override
    public void run() {
        if (inputFileForDecryption == null || inputFileForDecryption.length < 3) {
            System.err.println("Please provide a file to encrypt!");
            return;
        }

        if (inputPasswordForDecryption == null || inputPasswordForDecryption.length <= 8) {
            System.err.println("Please provide a valid password!");
            return;
        }

        File fileToDecrypt = inputFileForDecryption[0];
        File IV = inputFileForDecryption[1];
        File salt = inputFileForDecryption[2];
        String password = new String(inputPasswordForDecryption);

        DecryptService dP = new DecryptService();
        try {
            dP.decryptWithPassword(fileToDecrypt, IV, salt, password);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
