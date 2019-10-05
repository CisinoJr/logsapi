package br.com.cisinojr.logsapi.service.dto.builder;

import br.com.cisinojr.logsapi.service.dto.SearchCriteria;

public final class SearchCriteriaBuilder {

    private String key;
    private String operation;
    private Object value;

    private SearchCriteriaBuilder() {
    }

    public static SearchCriteriaBuilder aSearchCriteria() {
        return new SearchCriteriaBuilder();
    }

    public SearchCriteriaBuilder withKey(String key) {
        this.key = key;
        return this;
    }

    public SearchCriteriaBuilder withOperation(String operation) {
        this.operation = operation;
        return this;
    }

    public SearchCriteriaBuilder withValue(Object value) {
        this.value = value;
        return this;
    }

    public SearchCriteria build() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setKey(key);
        searchCriteria.setOperation(operation);
        searchCriteria.setValue(value);
        return searchCriteria;
    }

}
