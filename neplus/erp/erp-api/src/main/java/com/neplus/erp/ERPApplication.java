package com.neplus.erp;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by summer on 2017/12/25.
 */
//@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = {"com.neplus"}, exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
@EnableCaching
@EnableTransactionManagement
@Slf4j
@Controller
public class ERPApplication
{
    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args)
    {
        SpringApplication.run(ERPApplication.class, args);
    }

    @RequestMapping("shutdown")
    public String stopApp()
    {
        SpringApplication.exit(applicationContext);
        return "stopped ...";
    }

}
