module com.crm.mgr.mapper {
    requires spring.beans;
    requires spring.data.jpa;
    requires org.mapstruct;
    requires com.crm.mgr.dto;
    requires com.crm.mgr.entity;

    exports com.crm.mgr.mapper;
}