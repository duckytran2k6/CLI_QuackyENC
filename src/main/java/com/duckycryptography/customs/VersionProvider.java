package com.duckycryptography.customs;

import picocli.CommandLine;

import java.io.InputStream;
import java.util.Properties;

public class VersionProvider implements CommandLine.IVersionProvider {
    @Override
    public String[] getVersion() throws Exception {
        Properties props = new Properties();

        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("git.properties")) {
            if (stream == null) {
                return new String[]{"DuckyTool version info not available"};
            }

            props.load(stream);
            String version = props.getProperty("git.build.version", "unknown");
            String commit = props.getProperty("git.commit.id.abbrev", "unknown");
            String branch = props.getProperty("git.branch", "unknown");

            return new String[]{
                    "DuckyTool v" + version,
                    "Commit: " + commit,
                    "Branch: " + branch
            };
        }
    }
}
