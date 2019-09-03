package com.example.analyzer.core;

import java.io.Reader;
import java.util.List;

public interface LogProcessing {
    /**
     * Метод обработки файла с логами.
     * @param reader поток данных с логами.
     */
    void run(Reader reader);

    /**
     * Результат обработки файла с логами.
     * @see Incident
     * @return список интервалов с большим количеством отказов
     */
    List<Incident> getIncidentsList();
}
