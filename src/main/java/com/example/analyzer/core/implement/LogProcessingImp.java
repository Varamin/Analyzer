package com.example.analyzer.core.implement;

import com.example.analyzer.core.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class LogProcessingImp implements LogProcessing {
    private ErrorRequest errorRequest;
    private Parser parser;
    private float percentNotAvailable;
    private List<Incident> incidentsList = new ArrayList<>();

    public LogProcessingImp(ErrorRequest errorRequest, Parser parser, float percentNotAvailable) {
        this.errorRequest = errorRequest;
        this.parser = parser;
        this.percentNotAvailable = percentNotAvailable;
    }

    @Override
    public List<Incident> getIncidentsList() {
        return incidentsList;
    }

    @Override
    public void run(Reader reader) {
        long countAllRequest = 0;
        long countAllError = 0;
        long countRequest = 0;
        long countError = 0;
        float currentPercentAvailable = 0.0f;
        Incident incident = null;
        Request request = null;
        incidentsList.clear();

        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            String requestStr;
            while ((requestStr = bufferedReader.readLine()) != null) {
                request = parser.strToRequestObj(requestStr);
                if (request == null) continue;

                countAllRequest++;
                countRequest++;
                if (errorRequest.check(request)) {
                    countAllError++;
                    countError++;
                }
                currentPercentAvailable = this.calcPercentAvailable(countAllRequest, countAllError);
                if (currentPercentAvailable < this.percentNotAvailable) {
                    incident = new Incident(request.getDateSend());
                } else if (incident != null) {
                    incident.setDaEndIncident(request.getDateSend());
                    incident.setPercentAvailable(this.calcPercentAvailable(
                            countRequest - 1, //не учитываем текущий запрос, т.к. показатель вырос
                            countError
                    ));
                    this.incidentsList.add(incident);
                    incident = null;
                    countRequest = 0;
                    countError = 0;
                }
            }
            //Если по окончанию лога показатель не вернется в допустимую границу, то конец интервала = конец лога
            if (incident != null) {
                incident.setDaEndIncident(request.getDateSend());
                incident.setPercentAvailable(currentPercentAvailable);
                this.incidentsList.add(incident);
            }
        } catch (IOException errorReadStream) {
            errorReadStream.printStackTrace();
        }

    }

    private float calcPercentAvailable(long countRequest, long countError) {
        float percentNow = 0f;
        if (countRequest != 0) {
            percentNow = ((countRequest - countError) * 100f) / countRequest;
        }
        return BigDecimal.valueOf(percentNow).setScale(2, RoundingMode.UP).floatValue();
    }
}
