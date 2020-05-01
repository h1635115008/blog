package cn.aftertomorrow.controller;

import cn.aftertomorrow.util.ImageResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class FileUploadController {

    @RequestMapping("upload")
    @ResponseBody
    public ImageResponse handleFormUpload(@RequestParam("upload_file") MultipartFile uploadFile, HttpServletRequest request) {
        ImageResponse imageResponse = new ImageResponse();
        if (uploadFile != null) {
            String originalFilename = uploadFile.getOriginalFilename();
            String format = originalFilename.substring(originalFilename.lastIndexOf("."));
            String dirPath = request.getServletContext().getRealPath("/img/");
            File file = new File(dirPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            String fileName = UUID.randomUUID() + format;
            try {
                uploadFile.transferTo(new File(dirPath + fileName));
                imageResponse.setSuccess("true");
                imageResponse.setFile_path(request.getContextPath() + "/img/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
                imageResponse.setSuccess("false");
            }
        }
        return imageResponse;
    }
}
