package br.com.cisinojr.logsapi.service;

import br.com.cisinojr.logsapi.service.dto.LogInformationDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public interface FileService {

    /**
     * Read log file and save its information.
     *
     * @param file Log
     * @return log information.
     */
    Set<LogInformationDTO> readAndStoreLogInformation(MultipartFile file);

}
