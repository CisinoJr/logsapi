package br.com.cisinojr.logsapi.service.impl;

import br.com.cisinojr.logsapi.domain.LogInformation;
import br.com.cisinojr.logsapi.repository.LogInformationRepository;
import br.com.cisinojr.logsapi.service.LogInformationService;
import br.com.cisinojr.logsapi.service.dto.LogInformationDTO;
import br.com.cisinojr.logsapi.service.dto.SearchCriteria;
import br.com.cisinojr.logsapi.service.mapper.LogInformationMapper;
import javafx.beans.binding.BooleanExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static br.com.cisinojr.logsapi.service.specification.LogInformationSpecification.hasIp;
import static br.com.cisinojr.logsapi.service.specification.LogInformationSpecification.isBetween;

/**
 * Service Implementation for managing Aula.
 */
@Service
@Transactional
public class LogInformationServiceImpl implements LogInformationService {

    private final Logger log = LoggerFactory.getLogger(LogInformationServiceImpl.class);

    private final LogInformationRepository logInformationRepository;

    private final LogInformationMapper logInformationMapper;

    public LogInformationServiceImpl(LogInformationRepository logInformationRepository,
                                     LogInformationMapper logInformationMapper) {
        this.logInformationRepository = logInformationRepository;
        this.logInformationMapper = logInformationMapper;
    }

    /**
     * Save a log information.
     *
     * @param logInformationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LogInformationDTO save(LogInformationDTO logInformationDTO) {
        log.debug("Request to save LogInformation : {}", logInformationDTO);
        LogInformation logInformation = logInformationMapper.toEntity(logInformationDTO);
        logInformation = logInformationRepository.save(logInformation);
        return logInformationMapper.toDto(logInformation);
    }

    /**
     * Save a log information in batch.
     *
     * @return collection of entities.
     */
    @Override
    public Set<LogInformationDTO> saveAll(Set<LogInformationDTO> dtos) {
        Set<LogInformation> entities = dtos.stream().map(logInformationMapper::toEntity).collect(Collectors.toSet());
        Iterable<LogInformation> iterable = Collections.unmodifiableSet(entities);
        return logInformationRepository.saveAll(iterable)
                .stream()
                .map(logInformationMapper::toDto)
                .collect(Collectors.toSet());
    }

    /**
     * Get all the logs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<LogInformationDTO> findAll(Pageable pageable, SearchCriteria searchCriteria) {
        log.debug("Request to get all LogInformation");
        Page<LogInformationDTO> result = null;

        if (searchCriteria.getIp() != null) {
            result = logInformationRepository.findAll(hasIp(searchCriteria.getIp()), pageable)
                    .map(logInformationMapper::toDto);
        }

        if (searchCriteria.getStartDate() != null && searchCriteria.getEndDate() != null) {
            result = logInformationRepository.findAll(isBetween(searchCriteria.getStartDate(), searchCriteria.getEndDate()), pageable)
                    .map(logInformationMapper::toDto);
        }

        return result;
    }

    /**
     * Get the "id" LogInformation.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    public Optional<LogInformationDTO> findOne(Long id) {
        log.debug("Request to get LogInformation : {}", id);
        return logInformationRepository.findById(id)
                .map(logInformationMapper::toDto);
    }

    /**
     * Delete the "id" LogInformation.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LogInformation : {}", id);
        logInformationRepository.deleteById(id);
    }

}
