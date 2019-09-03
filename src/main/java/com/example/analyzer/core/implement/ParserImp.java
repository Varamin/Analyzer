package com.example.analyzer.core.implement;

import com.example.analyzer.core.Parser;
import com.example.analyzer.core.Request;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

public class ParserImp implements Parser {
    private final int timeSendField;
    private final int responseStatusField;
    private final int requestTimeField;

    public ParserImp(int timeSendField, int responseStatusField, int requestTimeField) {
        this.timeSendField = timeSendField;
        this.responseStatusField = responseStatusField;
        this.requestTimeField = requestTimeField;
    }

    @Override
    public Request strToRequestObj(String strRequest) {
        String[] filds = strRequest.split(" ");
        Request request = null;
        try {
            LocalDateTime dateSend = this.strToLocalDateTime(filds[this.timeSendField].substring(1));
            String responseStatus = filds[this.responseStatusField];
            float requestTime = Float.parseFloat(filds[this.requestTimeField]);
            request = new Request(
                    dateSend,
                    responseStatus,
                    requestTime
            );
        } catch (Exception ex) {
            System.out.println("Ошибка преобразования строки в объект Request");
            System.out.println("Строка " + strRequest);
        }

        return request;
    }

    public LocalDateTime strToLocalDateTime(String strDate) {
        LocalDateTime localDateTime = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy:HH:mm:ss");
            TemporalAccessor temporalAccessor = formatter.parse(strDate);
            localDateTime = LocalDateTime.from(temporalAccessor);
        } catch (DateTimeParseException dateTimeParseException) {
            System.out.println("Ошибка преобразования строки в дату");
            throw dateTimeParseException;
        }
        return localDateTime;
    }
}
