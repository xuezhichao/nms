package com.byxf.nms.controller;


import com.baidu.ueditor.ActionEnter;
import com.byxf.nms.model.BusinessErrorCode;
import com.byxf.nms.model.BusinessSucCode;
import com.byxf.nms.model.CommonResponse;
import com.byxf.nms.model.OperateMsg;
import com.byxf.nms.model.Picture;
import com.byxf.nms.util.DateUtil;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

/**
 * 文件上传
 */
@RestController
@RequestMapping("/image")
public class ImageUpload {

    @Value("${imageURL}")
    private String imageURL;
    @Value("${remoteServer}")
    private String remoteServer;

    private static final String VERSION = "1.0.0";

    /**
     * 文件上传 base64加密的图片保存
     */
    @RequestMapping(value = "uploadHeaderImage", method = RequestMethod.POST)
    public CommonResponse uploadHeaderImage(@RequestBody Picture pic) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            UUID name = UUID.randomUUID();
            Integer date = DateUtil.getCurIntDate();
            File targetFile = new File(imageURL+"topImg/" + date + "/");
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            FileOutputStream write = new FileOutputStream(new File(imageURL+"topImg/" + date + "/" + name + ".png"));
            byte[] decoderBytes = new byte[1024];
            decoderBytes = decoder.decodeBuffer(pic.getImgCode().split(",")[1]);
            write.write(decoderBytes);
            write.close();
            return new CommonResponse(OperateMsg.UPLOADHEADERIMAGE.getCode(),
                    DateUtil.date2Stringhms(new Date()), VERSION,
                    BusinessSucCode.EXEC_SUCC.getCode(), OperateMsg.UPLOADHEADERIMAGE.getMsg(),
                    remoteServer + "/topImg/" + date + "/" + name + ".png");
        } catch (IOException e) {
            //捕捉异常进行处理
            e.printStackTrace();
            return new CommonResponse(OperateMsg.UPLOADHEADERIMAGE.getCode(),
                    DateUtil.date2Stringhms(new Date()), VERSION,
                    BusinessErrorCode.ERROR_SYSTEM.getCode(), "封面上传失败！", "");
        }
    }

    //处理文件上传
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    CommonResponse upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {
            String contentType = file.getContentType();   //图片文件类型
            UUID name = UUID.randomUUID();
            Integer date = DateUtil.getCurIntDate();
            //文件存放路径
            String filePath = imageURL + date + "/";
            //调用文件处理类FileUtil，处理文件，将文件写入指定位置
            try {
                uploadFile(file.getBytes(), filePath, name.toString() + ".png");
            } catch (Exception e) {
                // TODO: handle exception
            }
            // 返回图片的存放路径
            return new CommonResponse(OperateMsg.UPLOAD.getCode(),
                    DateUtil.date2Stringhms(new Date()), VERSION,
                    BusinessSucCode.EXEC_SUCC.getCode(), OperateMsg.UPLOAD.getMsg(),
                    filePath + name.toString() + ".png");
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResponse(OperateMsg.UPLOAD.getCode(),
                    DateUtil.date2Stringhms(new Date()), VERSION,
                    BusinessErrorCode.ERROR_SYSTEM.getCode(), "文件上传失败！", "");
        }
    }

    //文件下载相关代码
    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public String downloadFile(@RequestParam String fileUrl, HttpServletResponse response) {
        if (fileUrl != null) {
            File file = new File(fileUrl);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + fileUrl);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("文件下载成功-------封面");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {

        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

}
