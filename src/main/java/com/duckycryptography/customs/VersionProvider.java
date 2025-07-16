package com.duckycryptography.customs;

import picocli.CommandLine;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class VersionProvider implements CommandLine.IVersionProvider {
    @Override
    public String[] getVersion() {
        Properties props = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("versions.properties")) {
            props.load(inputStream);
            return new String[]{"DuckyTool v" + props.getProperty("version")};
        } catch (IOException e) {
            return new String[]{"DuckyTool version info not available"};
        }
    }
}
