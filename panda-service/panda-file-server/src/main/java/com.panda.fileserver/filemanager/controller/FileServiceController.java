package com.panda.fileserver.filemanager.controller;

import com.panda.common.mvc.Response;
import com.panda.fileserver.filemanager.dto.FileInfo;
import com.panda.fileserver.filemanager.service.FileManagerService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-04  15:59
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
public class FileServiceController {

    @Autowired
    private FileManagerService fileManagerService;



    @PostMapping("/upload/{dir}")
    @ApiOperation(value = "文件上传", notes = "文件上传，form-data,key=file;fileType:pic、file", response = String.class, tags = {"UploadController",})
    public Response<String> uploadFile(HttpServletRequest request, MultipartFile file ,@PathVariable("dir") String dir) {

        try {
            InputStream inputStream = file.getInputStream();
            String md5= DigestUtils.md5Hex(file.getBytes());
            String uuid = fileManagerService.uploadFileToCos(inputStream,FileInfo.builder()
                    .name(file.getOriginalFilename())
                    .type(0)
                    .directory(dir)
                    .md5(md5).build());
            return Response.createSuc(uuid);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.createErr("");
    }
}
