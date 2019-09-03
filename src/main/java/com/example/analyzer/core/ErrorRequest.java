package com.example.analyzer.core;

public interface ErrorRequest {
    /**
     * Проверка запроса на корректность.
     * @see Request
     * @param request проверяемый запрос
     * @return результат проверки.
     */
    boolean check(Request request);
}
