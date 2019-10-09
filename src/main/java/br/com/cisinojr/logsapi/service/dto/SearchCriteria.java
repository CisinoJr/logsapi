package br.com.cisinojr.logsapi.service.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class SearchCriteria {

    private String ip;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchCriteria that = (SearchCriteria) o;
        return Objects.equals(ip, that.ip) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, startDate, endDate);
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "ip='" + ip + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

}