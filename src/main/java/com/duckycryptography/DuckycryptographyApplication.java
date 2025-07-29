package com.duckycryptography;

import com.duckycryptography.CLI.RootCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import picocli.CommandLine;

@SpringBootApplication
public class DuckycryptographyApplication implements CommandLineRunner {

	@Autowired
	private RootCommand mainCommand;

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(DuckycryptographyApplication.class, args);
	}

	@Override
	public void run(String... args) {
		new CommandLine(mainCommand).execute(args);
	}
}

