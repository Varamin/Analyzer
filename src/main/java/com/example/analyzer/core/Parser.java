package com.example.analyzer.core;

public interface Parser {
    /**
     * Преобразование строки в объект типа Request
     * @see Request
     * @param strRequest строка запроса
     * @return объект типа Request. В случае ошибки обработки вернется null
     */
    Request strToRequestObj(String strRequest);
}
