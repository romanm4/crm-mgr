module com.crm.mgr.repo {
    requires spring.context;
    requires spring.boot;
    requires spring.core;
    requires spring.data.jpa;
    requires spring.data.commons;
    requires com.crm.mgr.entity;

    exports com.crm.mgr.repo;
}