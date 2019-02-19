package com.byxf.nms;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ljj
 * @version 1.0.0
 * @ClassName NmsOmsMain
 * @Description 新闻发布系统web启动类
 * @Date 2019.02.19
 */
@SuppressWarnings("deprecation")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableScheduling
@ImportResource({"classpath:dubbo-config.xml"})
@EnableSwagger2
public class NmsWebMain extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(NmsWebMain.class);
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = null;
        SpringApplication app = null;
        Log log = LogFactory.getLog(NmsWebMain.class);

        try {
            app = new SpringApplication(NmsWebMain.class);
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
