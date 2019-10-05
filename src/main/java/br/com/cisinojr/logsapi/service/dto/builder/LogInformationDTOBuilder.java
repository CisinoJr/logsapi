package br.com.cisinojr.logsapi.service.dto.builder;

import br.com.cisinojr.logsapi.service.dto.LogInformationDTO;

import java.time.LocalDateTime;

public final class LogInformationDTOBuilder {
    private Long id;
    private LocalDateTime logDate;
    private String ip;
    private String request;
    private Integer httpStatusCode;
    private String userAgent;

    private LogInformationDTOBuilder() {
    }

    public static LogInformationDTOBuilder aLogInformationDTO() {
        return new LogInformationDTOBuilder();
    }

    public LogInformationDTOBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public LogInformationDTOBuilder withLogDate(LocalDateTime logDate) {
        this.logDate = logDate;
        return this;
    }

    public LogInformationDTOBuilder withIp(String ip) {
        this.ip = ip;
        return this;
    }

    public LogInformationDTOBuilder withRequest(String request) {
        this.request = request;
        return this;
    }

    public LogInformationDTOBuilder withHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
        return this;
    }

    public LogInformationDTOBuilder withUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public LogInformationDTO build() {
        LogInformationDTO logInformationDTO = new LogInformationDTO();
        logInformationDTO.setId(id);
        logInformationDTO.setLogDate(logDate);
        logInformationDTO.setIp(ip);
        logInformationDTO.setRequest(request);
        logInformationDTO.setHttpStatusCode(httpStatusCode);
        logInformationDTO.setUserAgent(userAgent);
        return logInformationDTO;
    }
}
