package com.duckycryptography.CLI;

import com.duckycryptography.customs.CustomHelpDisplay;
import com.duckycryptography.customs.CustomInfoDisplay;
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

    @CommandLine.Option(names = {"-info", "--information"}, description = "Information and requirements for the tool!")
    boolean information;

    @CommandLine.Option(names = {"-lim", "--limits"}, description = "Information about the default file and size limits!")
    boolean limits;

    @Override
    public void run() {
        if (help) {
            CustomHelpDisplay.displayHelp();
            return;
        }

        if (version) {
            System.out.println("Ducky-tool v2.10.13");
            return;
        }

        if (information) {
            CustomInfoDisplay.displayInfo();
            return;
        }

        if (limits) {
            System.out.println("The file type MUST BE a text file (.txt)\n Min-Max Files: 1-20\n Min-Max File Size: 1-100 MB");
            return;
        }

        System.out.println("Please enter -h or --help to see available commands!");
    }

}
