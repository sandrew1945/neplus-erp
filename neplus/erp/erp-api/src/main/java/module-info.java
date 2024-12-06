module com.sandrew.general.authcenter.api {
    requires com.neplus.erp.service;
    requires com.neplus.erp.persistent;
    requires lombok;
    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.tx;
    requires spring.web;
    requires org.mybatis;
    requires org.mybatis.spring;
    requires spring.aop;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires spring.data.redis;
    requires jakarta.annotation;
    requires tomcat.embed.core;
    requires org.apache.shiro.core;
    requires spring.core;
    requires spring.webmvc;
    requires commons.collections;
    requires org.aspectj.weaver;
    requires org.slf4j;
    requires org.apache.shiro.web;
    requires commons.lang3;
    requires org.apache.shiro.spring;
    requires org.apache.shiro.lang;
    requires org.apache.logging.log4j;

    exports com.neplus.erp.config to spring.beans, spring.context;
    exports com.neplus.erp.interceptor to spring.beans, spring.context, spring.aop;
    exports com.neplus.erp to spring.beans, spring.context;
    exports com.neplus.erp.controller to spring.beans, spring.context, spring.web, spring.aop;
    exports com.neplus.erp.config.shiro.separate to spring.core;

    opens com.neplus.erp to spring.core;
    opens com.neplus.erp.config to spring.core;
    opens com.neplus.erp.interceptor to spring.core;
    opens com.neplus.erp.controller to spring.core;
    opens com.neplus.erp.param to spring.core;
    opens com.neplus.erp.config.shiro to spring.core;
    opens com.neplus.erp.config.shiro.session to spring.core;
}