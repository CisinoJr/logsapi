package br.com.cisinojr.logsapi.service.specification;

import br.com.cisinojr.logsapi.LogsapiApplication;
import br.com.cisinojr.logsapi.repository.LogInformationRepository;
import br.com.cisinojr.logsapi.service.dto.LogInformationDTO;
import br.com.cisinojr.logsapi.service.dto.builder.LogInformationDTOBuilder;

import br.com.cisinojr.logsapi.service.mapper.LogInformationMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static br.com.cisinojr.logsapi.service.specification.LogInformationSpecification.hasIp;
import static org.hamcrest.collection.IsIn.isIn;
import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsNot.not;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LogsapiApplication.class)
@Transactional
class LogInformationSpecificationTest {

    @Autowired
    private LogInformationRepository repository;

    @Autowired
    private LogInformationMapper mapper;

    private LogInformationDTO logInformation;

    private LogInformationDTO loginInformation2;

    @Test
    @Before
    void init() {
        logInformation = LogInformationDTOBuilder.aLogInformationDTO()
                .withLogDate(LocalDateTime.now())
                .withHttpStatusCode(200)
                .withIp("192.168.162.248")
                .withUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                .withRequest("GET / HTTP/1.1")
                .build();

        repository.save(mapper.toEntity(logInformation));

        loginInformation2 = LogInformationDTOBuilder.aLogInformationDTO()
                .withLogDate(LocalDateTime.now().plusDays(10))
                .withHttpStatusCode(200)
                .withIp("192.168.111.153")
                .withUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                .withRequest("GET / HTTP/1.1")
                .build();

        repository.save(mapper.toEntity(logInformation));

        assertThat(repository.findAll(), hasSize(2));
    }

    @Test
    void assertThatExistLogWithGivenIpAddress() {
        String ip = "192.168.162.248";
        List<LogInformationDTO> entities = repository.findAll(hasIp(ip)).stream().map(mapper::toDto).collect(Collectors.toList());

        assertThat(logInformation, isIn(entities));
        assertThat(loginInformation2, not(entities));
    }

    @Test
    void assertThatExistsLogInPeriod() {
    }
}