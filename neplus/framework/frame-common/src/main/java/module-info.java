module com.neplus.framework.common {
    requires com.neplus.framework.core;
    requires tomcat.embed.core;
    requires org.apache.shiro.core;
    requires spring.beans;
    requires spring.web;
    requires spring.context;
    requires jakarta.annotation;
    requires org.slf4j;
    requires lombok;

    exports com.neplus.framework.common.controller;
}