package br.com.cisinojr.logsapi.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing file upload.
 */
@RestController
@RequestMapping("/upload")
public class UploadResource {

    /**
     * POST upload a single file.
     *
     * @param file log.
     * @return log information.
     */
    @PostMapping
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok().headers(null).body(file.getOriginalFilename());
    }

    /**
     * POST /multiple Upload multiple files.
     *
     * @param files a list of files.
     * @return logs information.
     */
    @PostMapping("/multiple")
    public List<ResponseEntity<String>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::upload)
                .collect(Collectors.toList());
    }

}
