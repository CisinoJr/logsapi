package br.com.cisinojr.logsapi.service.impl;

import br.com.cisinojr.logsapi.service.FileService;
import br.com.cisinojr.logsapi.service.LogInformationService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {

    private final LogInformationService loginInformationService;

    public FileServiceImpl(LogInformationService loginInformationService) {
        this.loginInformationService = loginInformationService;
    }

    @Override
    public boolean fileUpload(MultipartFile file) {
        return false;
    }


    private boolean readLogFile(MultipartFile file) {
        return false;
    }

}
