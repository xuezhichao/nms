package com.byxf.nms.controller;

import com.baidu.ueditor.ActionEnter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于处理关于ueditor插件相关的请求
 * @author
 * @date 2017-11-29
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/ueditor")
public class UeditorController {

    @Value("${imageURL}")
    private String imageURL;
    @Value("${resourcePath}")
    private String resourcePath;

    @RequestMapping(value = "/exec")
    @ResponseBody
    public String exec(HttpServletRequest request, HttpServletResponse response){
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            String rootPath = request.getSession().getServletContext().getRealPath("/");
//            D:/Git/upload
            System.out.println(rootPath);
            String exec = new ActionEnter(request, imageURL,resourcePath).exec();
            System.out.println(exec);
            return exec;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
