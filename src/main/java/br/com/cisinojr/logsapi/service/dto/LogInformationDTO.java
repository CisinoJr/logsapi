package br.com.cisinojr.logsapi.service.dto;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * A DTO representing the logs information.
 */
public class LogInformationDTO {

    private Long id;

    private LocalDateTime logDate;

    private String ip;

    private String request;

    private Integer httpStatusCode;

    private String userAgent;

    public LogInformationDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLogDate() {
        return logDate;
    }

    public void setLogDate(LocalDateTime logDate) {
        this.logDate = logDate;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogInformationDTO that = (LogInformationDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(logDate, that.logDate) &&
                Objects.equals(ip, that.ip) &&
                Objects.equals(request, that.request) &&
                Objects.equals(httpStatusCode, that.httpStatusCode) &&
                Objects.equals(userAgent, that.userAgent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, logDate, ip, request, httpStatusCode, userAgent);
    }

    @Override
    public String toString() {
        return "LogInformationDTO{" +
                "id=" + id +
                ", logdate=" + logDate +
                ", ip='" + ip + '\'' +
                ", request='" + request + '\'' +
                ", httpStatusCode=" + httpStatusCode +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }

}
