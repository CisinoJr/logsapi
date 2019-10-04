package br.com.cisinojr.logsapi.service.impl;

import br.com.cisinojr.logsapi.domain.LogInformation;
import br.com.cisinojr.logsapi.repository.LogInformationRepository;
import br.com.cisinojr.logsapi.service.LogInformationService;
import br.com.cisinojr.logsapi.service.dto.LogInformationDTO;
import br.com.cisinojr.logsapi.service.mapper.LogInformationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

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
     * Get all the logs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    public Page<LogInformationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LogInformation");
        return logInformationRepository.findAll(pageable)
                .map(logInformationMapper::toDto);
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
