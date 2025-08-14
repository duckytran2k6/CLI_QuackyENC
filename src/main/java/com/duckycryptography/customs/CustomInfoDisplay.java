package com.duckycryptography.customs;

public class CustomInfoDisplay {

    public static void displayInfo() {
        System.out.println("""
            📦 QuackyENC Information and Requirements:

            INFORMATION:
            🖥️ Supported OS:
               - Windows
               - macOS
               - Linux

            📂 Notes:
               - No external libraries needed
               - CLI runs offline

            ✅ Limitations:
               - The file type MUST BE a text file (.txt)
               - The amount of files can be uploaded for one session is from 1-20 files
               - The size of each file must not exceeding 100MB and can not be empty
            """);

    }
}
