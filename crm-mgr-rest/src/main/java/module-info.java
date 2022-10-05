module com.crm.mgr.rest {
    requires spring.web;
    requires spring.security.core;
    requires java.validation;
    requires com.crm.mgr.dto;
    requires com.crm.mgr.service;
    requires com.crm.mgr.entity;
    requires spring.data.commons;

    exports com.crm.mgr.rest;

    opens com.crm.mgr.rest to spring.core, spring.beans, spring.context;
}