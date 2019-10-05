package br.com.cisinojr.logsapi.web.rest;

import br.com.cisinojr.logsapi.service.FileService;
import br.com.cisinojr.logsapi.service.dto.LogInformationDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * REST controller for managing file upload.
 */
@RestController
@RequestMapping("/upload")
public class UploadResource {

    private final FileService fileService;

    public UploadResource(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * POST upload a single file.
     *
     * @param file log.
     * @return log information.
     */
    @PostMapping
    public Set<LogInformationDTO> upload(@RequestParam("file") MultipartFile file) {
        return fileService.readAndStoreLogInformation(file);
    }

    /**
     * POST /multiple Upload multiple files.
     *
     * @param files a list of files.
     * @return logs information.
     */
    @PostMapping("/multiple")
    public Set<Set<LogInformationDTO>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::upload)
                .collect(Collectors.toSet());
    }

}
