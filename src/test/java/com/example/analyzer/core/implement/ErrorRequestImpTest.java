package com.example.analyzer.core.implement;

import com.example.analyzer.core.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class ErrorRequestImpTest {
    private ErrorRequestImp errorRequestImp;
    private static final Pattern ERROR_RESPONSE_STATUS = Pattern.compile("5\\d{2}");
    private static final float AVAILABLE_RESPONSE_TIME = 10.55f;

    @BeforeEach
    void setUp() {
        errorRequestImp = new ErrorRequestImp(
                ERROR_RESPONSE_STATUS,
                AVAILABLE_RESPONSE_TIME
        );
    }

    @Test
    void checkSuccessRequest() {
        Request request = new Request(
                null,
                "200",
                10.50f
        );

        assertFalse(errorRequestImp.check(request));
    }

    @Test
    void checkErrorRequest() {
        Request request = new Request(
                null,
                "500",
                10.50f
        );

        assertTrue(errorRequestImp.check(request));
    }

    @Test
    void checkLongRequest() {
        Request request = new Request(
                null,
                "200",
                10.56f
        );

        assertTrue(errorRequestImp.check(request));
    }
}