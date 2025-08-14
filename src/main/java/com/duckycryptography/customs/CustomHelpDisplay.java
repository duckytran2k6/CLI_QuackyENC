package com.duckycryptography.customs;

public class CustomHelpDisplay {

    public static void displayHelp() {
        System.out.println("\nUsage: ducky-tool [COMMAND] [OPTIONS]");
        System.out.println("A CLI tool for encryption and decryption tasks.\n");
        System.out.println("Available commands:");
        System.out.println("  -h, --help              Display the available commands.");
        System.out.println("  -V, --version           Current version of the tool.");
        System.out.println("  -info, --information    Information about the default file and size limits!");
        System.out.println("  -e                      Encrypt mode using either password or key pair method.");
        System.out.println("  -d                      Decrypt mode using either password or key pair method.");
        System.out.println("  -kpg                    Generate a key pair.");
    }
}
