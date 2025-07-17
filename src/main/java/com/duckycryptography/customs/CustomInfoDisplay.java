package com.duckycryptography.customs;

public class CustomInfoDisplay {

    public static void displayInfo() {
        System.out.println("""
            📦 DuckyTool Information and Requirements:

            INFORMATION:
            🖥️ Supported OS:
               - Windows
               - macOS
               - Linux

            📂 Notes:
               - Java must be added to system PATH
               - No external libraries needed
               - CLI runs offline
            
            REQUIREMENTS TO RUN THIS TOOL:
            ✅ Java 17 or newer (JRE or JDK)
               ↳ Check with: java -version

            🔗 Download Java:
               https://adoptium.net/en-GB/temurin/releases/
            """);

    }
}
