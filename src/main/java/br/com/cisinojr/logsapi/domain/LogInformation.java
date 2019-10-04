package br.com.cisinojr.logsapi.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "log_information")
public class LogInformation implements Serializable {

    private static final long serialVersionUID = -3047663171660651782L;

    @Id
    @GeneratedValue(generator = "question_generator")
    @SequenceGenerator(
            name = "question_generator",
            sequenceName = "question_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime logDate;

    @Column
    private String ip;

    @Column
    private String request;

    @Column
    private Integer httpStatusCode;

    @Column
    private String userAgent;

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
        LogInformation logInformation = (LogInformation) o;
        return Objects.equals(id, logInformation.id) &&
                Objects.equals(logDate, logInformation.logDate) &&
                Objects.equals(ip, logInformation.ip) &&
                Objects.equals(request, logInformation.request) &&
                Objects.equals(httpStatusCode, logInformation.httpStatusCode) &&
                Objects.equals(userAgent, logInformation.userAgent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, logDate, ip, request, httpStatusCode, userAgent);
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", logdate=" + logDate +
                ", ip='" + ip + '\'' +
                ", request='" + request + '\'' +
                ", httpStatusCode=" + httpStatusCode +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }

}
