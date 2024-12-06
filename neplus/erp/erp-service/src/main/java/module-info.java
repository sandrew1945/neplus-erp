module com.neplus.erp.service {
    exports com.neplus.erp.service.util;
    exports com.neplus.erp.service;
    exports com.neplus.erp.service.impl;
    requires org.apache.shiro.core;
    requires com.neplus.erp.persistent;
    requires com.neplus.framework.core;
    requires spring.web;
    requires com.fasterxml.jackson.annotation;
    requires lombok;
    requires spring.beans;
    requires spring.context;
    requires jakarta.annotation;
    requires org.slf4j;

    opens com.neplus.erp.service.impl to spring.core;
    opens com.neplus.erp.service to spring.core;
}