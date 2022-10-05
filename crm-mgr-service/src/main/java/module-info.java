module com.crm.mgr.service {
    requires spring.context;
    requires spring.beans;
    requires spring.core;
    requires spring.webmvc;
    requires spring.aop;
    requires spring.web;
    requires spring.security.crypto;
    requires spring.security.core;
    requires spring.security.web;
    requires jjwt;
    requires jdk.unsupported;
    requires org.slf4j;
    requires org.apache.tomcat.embed.core;
    requires com.crm.mgr.entity;
    requires com.crm.mgr.dto;
    requires com.crm.mgr.repo;
    requires com.crm.mgr.mapper;
    requires com.crm.mgr.app.rabbitmq;
    requires spring.data.commons;

    exports com.crm.mgr.service.impl;
    exports com.crm.mgr.service.jwt;
    exports com.crm.mgr.service.log;
    exports com.crm.mgr.service.listener;

    opens com.crm.mgr.service.impl to spring.core;
    opens com.crm.mgr.service.jwt to spring.core;
}