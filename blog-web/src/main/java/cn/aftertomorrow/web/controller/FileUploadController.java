package cn.aftertomorrow.web.controller;

import cn.aftertomorrow.common.response.Result;
import cn.aftertomorrow.common.response.vo.file.ImageVO;
import cn.aftertomorrow.common.util.ResultUtils;
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

    @RequestMapping("upload/image")
    @ResponseBody
    public Result<ImageVO> handleFormUploadImage(@RequestParam("upload_file") MultipartFile uploadFile, HttpServletRequest request) {
        ImageVO imageVO = new ImageVO();
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
                imageVO.setFilePath(request.getContextPath() + "/img/" + fileName);
            } catch (IOException e) {
                throw new RuntimeException("上传失败", e);
            }
        }
        return ResultUtils.createSuccessResult(imageVO);
    }
}
