package br.com.cisinojr.logsapi.service;

import br.com.cisinojr.logsapi.service.dto.LogInformationDTO;
import br.com.cisinojr.logsapi.service.dto.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service for managing LogInformation.
 * @author cisinojr
 */
public interface LogInformationService {

    /**
     * Save a log information.
     *
     * @param logInformationDTO the entity to save
     * @return the persisted entity
     */
    LogInformationDTO save(LogInformationDTO logInformationDTO);

    /**
     * Save a log information in batch.
     *
     * @return collection of entities.
     */
    Set<LogInformationDTO> saveAll(Set<LogInformationDTO> dtos);

    /**
     * Get all the logs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LogInformationDTO> findAll(Pageable pageable, SearchCriteria searchCriteria);

    /**
     * Get the "id" avaliacao.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LogInformationDTO> findOne(Long id);

    /**
     * Delete the "id" avaliacao.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

}
