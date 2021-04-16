package cn.aftertomorrow.web.controller;

import cn.aftertomorrow.common.response.vo.file.ImageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传控制类
 *
 * @author huangming
 * @date 2019/09/26
 */
@Controller
public class FileUploadController {
    private final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @RequestMapping("upload/image")
    @ResponseBody
    public ImageVO handleFormUploadImage(@RequestParam("upload_file") MultipartFile uploadFile, HttpServletRequest request) {
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
                imageVO.setFile_path(request.getContextPath() + "/img/" + fileName);
            } catch (IOException e) {
                logger.error("上传失败", e);
                imageVO.setMsg("上传失败");
                imageVO.setSuccess(false);
                return imageVO;
            }
        }
        imageVO.setMsg("上传成功");
        imageVO.setSuccess(true);
        return imageVO;

    }
}
