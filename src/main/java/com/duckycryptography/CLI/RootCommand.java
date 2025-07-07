package com.duckycryptography.CLI;

import org.springframework.stereotype.Component;
import picocli.CommandLine;


@CommandLine.Command(
        name = "duckytool",
        subcommands = {
                EncryptWithPasswordCommand.class,
                EncryptWithKeyPairCommand.class,
                DecryptWithKeyPairCommand.class,
                DecryptWithKeyPairCommand.class,
                KeyPairGeneratorCommand.class
        },
        description = "Tools for encryption"
)

@Component
public class RootCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello World!");
    }


}
