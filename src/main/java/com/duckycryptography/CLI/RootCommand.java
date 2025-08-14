package com.duckycryptography.cli;

import com.duckycryptography.customs.CustomHelpDisplay;
import com.duckycryptography.customs.CustomInfoDisplay;
import picocli.CommandLine;


@CommandLine.Command(
        name = "Quacky-ENC",
        subcommands = {
                EncryptCommand.class,
                DecryptCommand.class,
                KeyPairGeneratorCommand.class
        },
        description = "Tools for encryption"
)

public class RootCommand implements Runnable {

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        int exitCode = new CommandLine(new RootCommand()).execute(args);
        System.exit(exitCode);
    }

    @CommandLine.Option(names = {"-h", "--help"}, description = "Shows the available commands!")
    boolean help;

    @CommandLine.Option(names = {"-v", "--version"}, description = "Information about the current tool's version!")
    boolean version;

    @CommandLine.Option(names = {"-info", "--information"}, description = "Information and requirements for the tool!")
    boolean information;

    @Override
    public void run() {
        if (help) {
            CustomHelpDisplay.displayHelp();
            return;
        }

        if (version) {
            System.out.println("QuackyENC Release v1.0.0");
            return;
        }

        if (information) {
            CustomInfoDisplay.displayInfo();
            return;
        }

        System.out.println("Please enter -h or --help to see available commands!");
    }

}
