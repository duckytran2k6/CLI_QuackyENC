package com.duckycryptography.CLI;

import com.duckycryptography.service.EncryptService;
import com.duckycryptography.service.FilesSelectService;
import com.duckycryptography.service.ValidityCheckerService;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

@CommandLine.Command (
        name = "-pass",
        description = "Encryption using a provided password!"
)

public class EncryptWithPasswordCommand implements Runnable {

    @CommandLine.Option(names = {"-pw", "--password"}, required = true, description = "Please enter a password with a minimum of 8 characters!")
    private String password;

    @Override
    public void run() {
        List<File> files = FilesSelectService.selectMultipleFiles("Select the files you want to be encrypted!");

        if (!ValidityCheckerService.checkListLimit(files)) {
            return;
        }

        if (!ValidityCheckerService.validPassword(password)) {
            System.err.println("Please provide a valid password!");
            return;
        }

        EncryptService eP = new EncryptService();
        try {
            eP.encryptDataWithPassword(files, password);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
