package com.byxf.nms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ljj
 * @version 1.0.0
 * @ClassName NmsMain
 * @Description 新闻发布系统启动类
 * @Date 2019.01.25
 */
@SuppressWarnings("deprecation")
@SpringBootApplication
@EnableScheduling
@ImportResource({"classpath:dubbo-config.xml"})
public class NmsCoreMain extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(NmsCoreMain.class);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = null;
        SpringApplication app = null;
        Log log = LogFactory.getLog(NmsCoreMain.class);

        try {
            app = new SpringApplication(NmsCoreMain.class);
            ctx = app.run();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("nms application start up is false!");
            System.exit(0);
        }
        if (ctx == null || app == null) {
            log.error("nms application start up is false!");
            System.exit(0);
        }
    }
}