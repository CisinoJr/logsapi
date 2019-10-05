package br.com.cisinojr.logsapi.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    boolean fileUpload(MultipartFile file);

}
