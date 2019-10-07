package br.com.cisinojr.logsapi.web.rest;

import br.com.cisinojr.logsapi.service.LogInformationService;
import br.com.cisinojr.logsapi.service.dto.LogInformationDTO;
import br.com.cisinojr.logsapi.service.dto.SearchCriteria;
import br.com.cisinojr.logsapi.web.rest.errors.BadRequestAlertException;
import br.com.cisinojr.logsapi.web.rest.util.HeaderUtil;
import br.com.cisinojr.logsapi.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing LogInformation.
 */
@RestController
@RequestMapping("/logs")
public class LogInformationResource {
    private final Logger log = LoggerFactory.getLogger(LogInformationResource.class);

    private static final String ENTITY_NAME = "logInformation";

    private final LogInformationService logInformationService;

    public LogInformationResource(LogInformationService logInformationService) {
        this.logInformationService = logInformationService;
    }

    /**
     * POST  /logs : Create a new logInformation.
     *
     * @param logInformationDTO the logInformationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new logInformationDTO,
     * or with status 400 (Bad Request) if the logInformation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping
    public ResponseEntity<LogInformationDTO> createLogInformation(@RequestBody LogInformationDTO logInformationDTO) throws URISyntaxException {
        log.debug("REST request to save LogInformation : {}", logInformationDTO);
        if (logInformationDTO.getId() != null) {
            throw new BadRequestAlertException("A new logInformation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LogInformationDTO result = logInformationService.save(logInformationDTO);
        return ResponseEntity.created(new URI("/api/logs/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * PUT  /logs : Updates an existing logInformation.
     *
     * @param logInformationDTO the logInformationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated logInformationDTO,
     * or with status 400 (Bad Request) if the logInformationDTO is not valid,
     * or with status 500 (Internal Server Error) if the logInformationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping
    public ResponseEntity<LogInformationDTO> updateLogInformation(@RequestBody LogInformationDTO logInformationDTO) throws URISyntaxException {
        log.debug("REST request to update logInformation : {}", logInformationDTO);
        if (logInformationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LogInformationDTO result = logInformationService.save(logInformationDTO);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, logInformationDTO.getId().toString()))
                .body(result);
    }

    /**
     * GET  /logs : get all the logInformation.
     *
     * @param pageable       the pagination information
     * @param searchCriteria the object with filter of the entities
     * @return the ResponseEntity with status 200 (OK) and the list of logInformation in body
     */
    @GetMapping
    public ResponseEntity<List<LogInformationDTO>> getAllLogInformation(Pageable pageable, @RequestBody(required = false) SearchCriteria searchCriteria) {
        log.debug("REST request to get a page of LogInformation");
        Page<LogInformationDTO> page = logInformationService.findAll(pageable, searchCriteria);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/logs");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /logs/:id : get the "id" LogInformation.
     *
     * @param id the id of the LogInformationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the LogInformationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<LogInformationDTO> getLogInformation(@PathVariable Long id) {
        log.debug("REST request to get LogInformation : {}", id);
        Optional<LogInformationDTO> logInformationDTO = logInformationService.findOne(id);

        if (!logInformationDTO.isPresent()) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        return ResponseEntity.ok().headers(null).body(logInformationDTO.get());
    }

    /**
     * DELETE  /logs/:id : delete the "id" LogInformation.
     *
     * @param id the id of the LogInformationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogInformation(@PathVariable Long id) {
        log.debug("REST request to delete LogInformation : {}", id);
        logInformationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
