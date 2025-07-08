package com.duckycryptography.CLI;

import org.springframework.stereotype.Component;
import picocli.CommandLine;


@CommandLine.Command(
        name = "duckytool",
        mixinStandardHelpOptions = true,
        subcommands = {
                EncryptCommand.class,
                DecryptCommand.class,
                KeyPairGeneratorCommand.class
        },
        description = "Tools for encryption"
)

@Component
public class RootCommand implements Runnable {

    @CommandLine.Option(
            names = "--option", description = "Here are the options."
    )
    String option;

    @Override
    public void run() {
            CommandLine.usage(this, System.out);
    }


}
