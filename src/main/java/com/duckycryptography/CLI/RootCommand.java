package com.duckycryptography.CLI;

import org.springframework.stereotype.Component;
import picocli.CommandLine;

@Component
@CommandLine.Command(name = "hello", description = "Says Hello")
public class RootCommand implements Runnable {
    public static void main(String[] args) {
         CommandLine.run(new RootCommand(), args);
    }

    @Override
    public void run() {
        System.out.println("Hello World!");
    }


}
