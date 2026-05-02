package com.duckycryptography.cli;

import com.duckycryptography.service.EncryptService;
import com.duckycryptography.service.FilesService;
import com.duckycryptography.service.ValidityCheckerService;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

@CommandLine.Command (
        name = "-pass",
        description = "Encryption using a provided password!"
)

public class EncryptWithPasswordCommand implements Runnable {
    @CommandLine.Parameters(index = "2", interactive = true, arity = "0..1", description = "Please enter a password with a minimum of 8 characters!")
    private char[] password;

    @Override
    public void run() {

        try {
            List<File> files = FilesService.selectMultipleFiles("Select the files you want to be encrypted!");

            if (!ValidityCheckerService.checkListLimit(files)) {
                return;
            }

            if (!ValidityCheckerService.validPassword(password)) {
                return;
            }

            EncryptService eP = new EncryptService();
                eP.encryptDataWithPassword(files, password);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
