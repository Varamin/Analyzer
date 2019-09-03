package com.example.analyzer.util;

import org.apache.commons.cli.*;

public abstract class CommandLineArgs {
    private static final Options APP_OPTIONS = new Options();
    private static CommandLine COMMAND_LINE;

    public static boolean initialization(String[] args) {
        boolean isInit = false;
        APP_OPTIONS.addOption(new Option("u", true, "Minimum acceptable level of availability (%)"));
        APP_OPTIONS.addOption(new Option("t", true, "Acceptable response time (milliseconds)"));
        CommandLineParser parser = new DefaultParser();
        try {
            COMMAND_LINE = parser.parse(APP_OPTIONS, args);
            if (COMMAND_LINE.hasOption("u") && COMMAND_LINE.hasOption("t")) {
                isInit = true;
            } else {
                System.out.println("Required parameters not set");
                System.out.println(APP_OPTIONS.toString());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return isInit;
    }

    public static String getAllOptions() {
        return APP_OPTIONS.getOptions().toString();
    }

    public static float getMinPercentAvailable() {
        return Float.parseFloat(COMMAND_LINE.getOptionValue("u"));
    }

    public static float getAvailableResponseTime() {
        return Float.parseFloat(COMMAND_LINE.getOptionValue("t"));
    }
}
