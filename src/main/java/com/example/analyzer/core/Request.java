package com.example.analyzer.core;

import java.time.LocalDateTime;
import java.util.Objects;

public class Request {
    private LocalDateTime dateSend;
    private String status;
    private float timeRequest;

    public Request(LocalDateTime dateSend, String status, float timeRequest) {
        this.dateSend = dateSend;
        this.status = status;
        this.timeRequest = timeRequest;
    }

    public LocalDateTime getDateSend() {
        return this.dateSend;
    }

    public void setDateSend(LocalDateTime dateSend) {
        this.dateSend = dateSend;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getTimeRequest() {
        return timeRequest;
    }

    public void setTimeRequest(float timeRequest) {
        this.timeRequest = timeRequest;
    }

    @Override
    public String toString() {
        return "Request{" +
                "dateSend=" + dateSend +
                ", status='" + status + '\'' +
                ", timeRequest=" + timeRequest +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Float.compare(request.getTimeRequest(), getTimeRequest()) == 0 &&
                Objects.equals(getDateSend(), request.getDateSend()) &&
                Objects.equals(getStatus(), request.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDateSend(), getStatus(), getTimeRequest());
    }
}
