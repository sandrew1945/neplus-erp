module com.neplus.erp.persistent {
    requires transitive com.neplus.framework.core;
    requires org.mapstruct;
    requires java.compiler;
    requires spring.context;
    requires lombok;
    requires java.sql;
    requires org.mybatis;

    exports com.neplus.erp.bean;
    exports com.neplus.erp.dictionary;
    exports com.neplus.erp.mapper;
    exports com.neplus.erp.model;
    exports com.neplus.erp.bean.login;
    exports com.neplus.erp.bean.usermanager;
    exports com.neplus.erp.bean.rolemanager;
    exports com.neplus.erp.mapper.custom;
    exports com.neplus.erp.bean.clientmanager;
    exports com.neplus.erp.bean.taskmanager;

}