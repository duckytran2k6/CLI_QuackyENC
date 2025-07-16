package com.duckycryptography.customs;

public class CustomHelpDisplay {

    public static void displayHelp() {
        System.out.println("\nUsage: ducky-tool [COMMAND] [OPTIONS]");
        System.out.println("A CLI tool for encryption and decryption tasks.\n");
        System.out.println("Options:");
        System.out.println("  -h, --help        Show this help message and exit.");
        System.out.println("  -V, --version     Print version information and exit.");
        System.out.println("  -lim, --limits    Information about the default file and size limits!\n");
        System.out.println("Commands:");
        System.out.println("  -e                Encrypt mode using either password or key pair method.");
        System.out.println("  -d                Decrypt mode using either password or key pair method.");
        System.out.println("  -kpg              Generate a key pair.");
    }
}
