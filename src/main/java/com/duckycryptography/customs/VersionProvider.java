package com.duckycryptography.customs;

import picocli.CommandLine;
import java.io.InputStream;
import java.util.Properties;

public class VersionProvider implements CommandLine.IVersionProvider {
    @Override
    public String[] getVersion() throws Exception {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("version.properties")) {
            props.load(input);
            return new String[]{props.getProperty("version", "v0.0.0")};
        }
    }
}
