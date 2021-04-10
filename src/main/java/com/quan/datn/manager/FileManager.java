package com.quan.datn.manager;

import com.quan.datn.common.CommonResponse;
import com.quan.datn.common.ExceptionResponse;
import com.quan.datn.common.MessageResponse;
import com.quan.datn.model.OtherFile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Date;


@Controller
public class FileManager {

    public OtherFile upload(MultipartFile file) {
        OtherFile fileResponse = new OtherFile();
        LocalDate localDate = LocalDate.now();
        StringBuilder pathName = new StringBuilder("uploads");
        pathName.append("/").append(localDate.getYear());
        pathName.append("/").append(localDate.getMonthValue());
        pathName.append("/").append(localDate.getDayOfMonth());
        Path path = Paths.get(pathName.toString());
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String contentType = file.getContentType();
        String nameFile = new Date().getTime() + "_" + file.getOriginalFilename();
        String pathFile = pathName.toString() + "/" + nameFile;
        fileResponse.setFileName(nameFile);
        fileResponse.setFilePath("/" + pathFile);
        fileResponse.setFileType(contentType);
        try {
            Files.copy(file.getInputStream(), path.resolve(nameFile),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            return new OtherFile();
        }
        return fileResponse;
    }

    public Object deleteFile(String filePath) throws ExceptionResponse {
        Path path = Paths.get(filePath);
        if (Files.exists(path)) {
            try {
                Files.delete(path);
                return new CommonResponse(HttpStatus.OK, MessageResponse.DELETE_FILE_SUCCESS);
            } catch (IOException e) {
                return new CommonResponse(HttpStatus.BAD_REQUEST, MessageResponse.AN_UNKNOWN_ERROR);
            }
        }
        return new CommonResponse(HttpStatus.FORBIDDEN, MessageResponse.FILE_NOT_EXITS);
    }

}
