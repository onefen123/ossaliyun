package com.fen.oss.controller;

import com.fen.oss.utils.AliyunOSS;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

@Controller
public class OSSuploadController {
    @ResponseBody
    @RequestMapping(value = "upload_post",method = RequestMethod.POST)
    public String upload_post(MultipartFile file){
        try {

            if(null != file){
                String filename = file.getOriginalFilename();
                if(!"".equals(filename.trim())){
                    File newFile = new File(filename);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    //上传到OSS
                    String uploadUrl = AliyunOSS.upload(newFile);

                }

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "1";

    }

    @RequestMapping(value = "upload",method = RequestMethod.GET)
    public String upload(){
        return "upload";
    }


}
