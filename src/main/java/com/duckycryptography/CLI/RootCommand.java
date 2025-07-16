package com.duckycryptography.CLI;

import org.springframework.stereotype.Component;
import picocli.CommandLine;


@CommandLine.Command(
        name = "ducky-tool",
        mixinStandardHelpOptions = true,
        version = "DuckyCyTool 1.0",
        subcommands = {
                EncryptCommand.class,
                DecryptCommand.class,
                KeyPairGeneratorCommand.class
        },
        description = "Tools for encryption"
)

@Component
public class RootCommand implements Runnable {

    @Override
    public void run() {
        System.out.println("Please enter -h or --help to see available commands!");
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new RootCommand()).execute(args);
        System.exit(exitCode);
    }
}
