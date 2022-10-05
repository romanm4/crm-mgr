module com.crm.mgr.entity {
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires spring.data.jpa;
    requires java.persistence;
    requires org.hibernate.orm.core;

    exports com.crm.mgr.entity;
    exports com.crm.mgr.specification;

    opens com.crm.mgr.entity to org.hibernate.orm.core, spring.core;
}