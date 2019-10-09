package br.com.cisinojr.logsapi.service.mapper;

import br.com.cisinojr.logsapi.domain.LogInformation;
import br.com.cisinojr.logsapi.service.dto.LogInformationDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity LogInformation and its DTO LogInformationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LogInformationMapper extends EntityMapper<LogInformationDTO, LogInformation> {

    LogInformationDTO toDto(LogInformation logInformation);

    LogInformation toEntity(LogInformationDTO logInformationDTO);

    default LogInformation fromId(Long id) {
        if (id == null) {
            return null;
        }

        LogInformation logInformation = new LogInformation();
        logInformation.setId(id);

        return logInformation;
    }

}
