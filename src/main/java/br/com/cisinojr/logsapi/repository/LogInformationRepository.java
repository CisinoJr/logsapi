package br.com.cisinojr.logsapi.repository;

import br.com.cisinojr.logsapi.domain.LogInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LogInformationRepository extends JpaRepository<LogInformation, Long>, JpaSpecificationExecutor<LogInformation> {
}
