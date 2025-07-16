package com.duckycryptography.CLI;

import com.duckycryptography.customs.CustomHelpDisplay;
import org.springframework.stereotype.Component;
import picocli.CommandLine;


@CommandLine.Command(
        name = "ducky-tool",
        subcommands = {
                EncryptCommand.class,
                DecryptCommand.class,
                KeyPairGeneratorCommand.class
        },
        description = "Tools for encryption"
)

@Component
public class RootCommand implements Runnable {

    @CommandLine.Option(names = {"-h", "--help"}, description = "Shows the available commands!")
    boolean help;

    @CommandLine.Option(names = {"-v", "--version"}, description = "Information about the current tool's version!")
    boolean version;

    @CommandLine.Option(names = {"-lim", "--limits"}, description = "Information about the default file and size limits!")
    boolean limits;

    @Override
    public void run() {
        if (help) {
            CustomHelpDisplay.displayHelp();
            return;
        }

        if (version) {
            System.out.println("Duckytool v1.0.0");
            return;
        }

        if (limits) {
            System.out.println("\uD83D\uDCE6 Min-Max Files: 1-20\n \uD83D\uDCC1 Min-Max File Size: 1-100 MB");
            return;
        }

        System.out.println("Please enter -h or --help to see available commands!");
    }

}
