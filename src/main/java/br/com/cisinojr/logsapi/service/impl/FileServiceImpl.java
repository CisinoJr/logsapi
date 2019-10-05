package br.com.cisinojr.logsapi.service.impl;

import br.com.cisinojr.logsapi.service.FileService;
import br.com.cisinojr.logsapi.service.LogInformationService;
import br.com.cisinojr.logsapi.service.dto.LogInformationDTO;
import br.com.cisinojr.logsapi.service.dto.builder.LogInformationDTOBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class FileServiceImpl implements FileService {

    private static final String SEPARATOR = "\\|";

    private final LogInformationService loginInformationService;

    public FileServiceImpl(LogInformationService loginInformationService) {
        this.loginInformationService = loginInformationService;
    }

    /**
     * Read log file and save its information.
     *
     * @param file Log
     * @return log information.
     */
    @Override
    public Set<LogInformationDTO> readAndStoreLogInformation(MultipartFile file) {
        return loginInformationService.saveAll(readLogFile(file));
    }

    private Set<LogInformationDTO> readLogFile(MultipartFile file) {
        Set<LogInformationDTO> result = new HashSet<>();

        try {
            File convertedFile = convert(file);
            BufferedReader br = new BufferedReader(new FileReader(convertedFile));
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(SEPARATOR);
                LogInformationDTO logInformationDTO = createDTO(values);
                result.add(logInformationDTO);
            }

            Files.delete(convertedFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    private LogInformationDTO createDTO(String[] values) {
        return LogInformationDTOBuilder.aLogInformationDTO()
                .withLogDate(stringToLocalDateTime(values[0]))
                .withIp(values[1])
                .withRequest(removeDoubleQuotes(values[2]))
                .withHttpStatusCode(Integer.parseInt(values[3]))
                .withUserAgent(removeDoubleQuotes(values[4]))
                .build();
    }

    private String removeDoubleQuotes(String value) {
        if (value.isEmpty()) {
            return null;
        }

        return value.replace("\"", "");
    }

    private File convert(MultipartFile file) throws IOException {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }

    private LocalDateTime stringToLocalDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return LocalDateTime.parse(dateTime, formatter);
    }

}
