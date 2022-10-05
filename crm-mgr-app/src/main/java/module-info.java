module com.crm.mgr.app {
    requires spring.context;
    requires spring.beans;
    requires spring.jdbc;
    requires spring.amqp;
    requires spring.rabbit;
    requires spring.web;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.boot.starter.amqp;
    requires spring.security.core;
    requires spring.security.crypto;
    requires spring.security.config;
    requires spring.security.web;
    requires spring.data.jpa;
    requires com.hazelcast.core;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires java.annotation;
    requires java.sql;
    requires liquibase.core;
    requires io.swagger.v3.core;
    requires io.swagger.v3.oas.annotations;
    requires io.swagger.v3.oas.models;
    requires com.crm.mgr.rest;
    requires com.crm.mgr.repo;
    requires com.crm.mgr.service;

    opens com.crm.mgr.app to spring.core, spring.beans, spring.context;
    opens com.crm.mgr.app.config to spring.core, spring.beans, spring.context;
}