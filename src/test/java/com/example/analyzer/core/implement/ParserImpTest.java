package com.example.analyzer.core.implement;

import com.example.analyzer.core.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserImpTest {
    private ParserImp parserImp;
    private static final int DATE_SEND_FIELD = 3;
    private static final int RESPONSE_STATUS_FIELD = 8;
    private static final int REQUEST_TIME_FIELD = 10;
    private static final String CORRECT_STRING_REQUEST = "192.168.32.181 - - [14/06/2017:16:47:02 +1000] " +
            "\"PUT /rest/v1.4/documents?zone=default&_rid=6076537c HTTP/1.1\" 200 2 44.510983 \"-\" \"@list-item-updater\" prio:0";

    @BeforeEach
    void setUp() {
        parserImp = new ParserImp(
                DATE_SEND_FIELD,
                RESPONSE_STATUS_FIELD,
                REQUEST_TIME_FIELD
        );
    }

    @Test
    void strToRequestObjSuccessParse() {
        Request request = new Request(
                LocalDateTime.of(2017, 6, 14, 16, 47, 2),
                "200",
                44.510983f
        );
        assertEquals(request, parserImp.strToRequestObj(CORRECT_STRING_REQUEST));
    }

    @Test
    void strToLocalDateTimeSuccessConvert() {
        String dateStr = "14/06/2017:16:47:02";
        LocalDateTime dateTimeExpect = LocalDateTime.of(2017, 6, 14, 16, 47, 2);

        assertEquals(dateTimeExpect, parserImp.strToLocalDateTime(dateStr));
    }
}