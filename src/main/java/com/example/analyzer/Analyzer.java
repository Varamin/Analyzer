package com.example.analyzer;

import com.example.analyzer.core.Incident;
import com.example.analyzer.core.implement.ErrorRequestImp;
import com.example.analyzer.core.implement.LogProcessingImp;
import com.example.analyzer.core.implement.ParserImp;
import com.example.analyzer.util.CommandLineArgs;

import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Analyzer {

    private static final int DATE_SEND_FIELD = 3;
    private static final int RESPONSE_STATUS_FIELD = 8;
    private static final int REQUEST_TIME_FIELD = 10;
    private static final Pattern ERROR_RESPONSE_STATUS = Pattern.compile("5\\d{2}");

    public static void main(String[] args) {

        if (!CommandLineArgs.initialization(args)) {
            System.out.println("Error initialization input arguments");
            return;
        }

        ErrorRequestImp errorRequestImp = new ErrorRequestImp(
                ERROR_RESPONSE_STATUS,
                CommandLineArgs.getAvailableResponseTime()
        );

        ParserImp parserImp = new ParserImp(
                DATE_SEND_FIELD,
                RESPONSE_STATUS_FIELD,
                REQUEST_TIME_FIELD
        );

        LogProcessingImp logProcessingImp = new LogProcessingImp(
                errorRequestImp,
                parserImp,
                CommandLineArgs.getMinPercentAvailable()
        );

        logProcessingImp.run(new InputStreamReader(System.in, UTF_8));

        logProcessingImp.getIncidentsList().stream()
                .sorted(Comparator.comparing(Incident::getDateStartIncident))
                .forEach(incident -> System.out.println(incident.toString()));
    }
}
