package com.example.analyzer.core;

import java.time.LocalDateTime;

public class Incident {
    private LocalDateTime dateStartIncident;
    private LocalDateTime daEndIncident;
    private float percentAvailable;

    public Incident(LocalDateTime dateStartIncident) {
        this.dateStartIncident = dateStartIncident;
    }

    public LocalDateTime getDateStartIncident() {
        return this.dateStartIncident;
    }

    public void setDateStartIncident(LocalDateTime dateStartIncident) {
        this.dateStartIncident = dateStartIncident;
    }

    public LocalDateTime getDaEndIncident() {
        return this.daEndIncident;
    }

    public void setDaEndIncident(LocalDateTime daEndIncident) {
        this.daEndIncident = daEndIncident;
    }

    public float getPercentAvailable() {
        return percentAvailable;
    }

    public void setPercentAvailable(float percentAvailable) {
        this.percentAvailable = percentAvailable;
    }

    @Override
    public String toString() {
        return "Incidents{" +
                "from = " + dateStartIncident +
                " to = " + daEndIncident +
                " percent available = " + percentAvailable +
                "}";
    }


}
