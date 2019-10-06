package br.com.cisinojr.logsapi.service.dto.builder;

import br.com.cisinojr.logsapi.service.dto.SearchCriteria;

import java.time.LocalDateTime;

public final class SearchCriteriaBuilder {
    
    private String ip;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private SearchCriteriaBuilder() {
    }

    public static SearchCriteriaBuilder aSearchCriteria() {
        return new SearchCriteriaBuilder();
    }

    public SearchCriteriaBuilder withIp(String ip) {
        this.ip = ip;
        return this;
    }

    public SearchCriteriaBuilder withStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public SearchCriteriaBuilder withEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public SearchCriteria build() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setIp(ip);
        searchCriteria.setStartDate(startDate);
        searchCriteria.setEndDate(endDate);
        return searchCriteria;
    }

}
