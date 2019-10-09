package br.com.cisinojr.logsapi.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    /**
     * Read log file and save its information.
     *
     * @param file Log
     * @return log information.
     */
    boolean readAndStoreLogInformation(MultipartFile file);

}
