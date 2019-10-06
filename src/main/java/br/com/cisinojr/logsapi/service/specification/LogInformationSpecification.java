package br.com.cisinojr.logsapi.service.specification;

import br.com.cisinojr.logsapi.domain.LogInformation;
import br.com.cisinojr.logsapi.domain.LogInformation_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class LogInformationSpecification {

    public static Specification<LogInformation> hasIp(String ip) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get(LogInformation_.ip), ip);
        };
    }

    public static Specification<LogInformation> isBetween(LocalDateTime start, LocalDateTime end) {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.between(root.get(LogInformation_.logDate), start, end);
        };
    }

}
