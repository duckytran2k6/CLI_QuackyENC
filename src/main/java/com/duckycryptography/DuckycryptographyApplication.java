package com.duckycryptography;

import com.duckycryptography.CLI.RootCommand;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import picocli.CommandLine;

@SpringBootApplication
public class DuckycryptographyApplication implements ApplicationRunner {

	private final RootCommand mainCommand;

	public DuckycryptographyApplication(RootCommand mainCommand) {
		this.mainCommand = mainCommand;
	}

	public static void main(String[] args) {
		SpringApplication.run(DuckycryptographyApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		new CommandLine(mainCommand).execute(args.getSourceArgs());
	}
}

