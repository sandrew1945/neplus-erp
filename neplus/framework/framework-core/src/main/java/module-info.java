module com.neplus.framework.core {
    requires java.base;
    requires lombok;
    requires com.fasterxml.jackson.annotation;
    requires java.datatransfer;
    requires java.desktop;
    requires tomcat.embed.core;
    requires spring.core;
    requires java.sql;
    requires spring.beans;
    requires spring.web;
    requires commons.codec;
    requires commons.lang3;
    requires org.mybatis;
    requires spring.data.redis;
    requires spring.context;
    requires org.apache.shiro.core;
    requires commons.beanutils;
    requires commons.io;
    requires org.slf4j;
    requires org.apache.logging.log4j;

    exports com.neplus.framework.core.annotation;
    exports com.neplus.framework.core.bean;
    exports com.neplus.framework.core.exception;
    exports com.neplus.framework.core.util;
    exports com.neplus.framework.core.mybatis;
    exports com.neplus.framework.core.result;
    exports com.neplus.framework.core.encrypt;
    exports com.neplus.framework.core.enums;
    exports com.neplus.framework.core.captcha;
    exports com.neplus.framework.core.shiro;
    exports com.neplus.framework.core.mybatis.dialect;
    exports com.neplus.framework.core.nosql;

    opens com.neplus.framework.core.nosql to spring.core;
}