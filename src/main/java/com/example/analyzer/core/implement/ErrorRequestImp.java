package com.example.analyzer.core.implement;

import com.example.analyzer.core.ErrorRequest;
import com.example.analyzer.core.Request;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ErrorRequestImp implements ErrorRequest {
    private final Pattern errorResponseStatus;
    private final float availableResponseTime;

    public ErrorRequestImp(Pattern errorResponseStatus, float availableResponseTime) {
        this.errorResponseStatus = errorResponseStatus;
        this.availableResponseTime = availableResponseTime;
    }

    @Override
    public boolean check(Request request) {
        boolean isSuccess = false;
        Matcher matcherStatus = this.errorResponseStatus.matcher(request.getStatus());
        if (matcherStatus.find() || request.getTimeRequest() > this.availableResponseTime) {
            isSuccess = true;
        }

        return isSuccess;
    }
}
