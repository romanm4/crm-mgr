module com.crm.mgr.app.rabbitmq {
    requires spring.amqp;
    requires spring.rabbit;
    requires spring.context;
    requires spring.boot;
    requires spring.core;
    requires org.slf4j;
    requires com.crm.mgr.dto;

    exports com.crm.mgr.app.rabbitmq.producer;
    exports com.crm.mgr.app.rabbitmq.property;
    exports com.crm.mgr.app.rabbitmq.tool;

    opens com.crm.mgr.app.rabbitmq.property to spring.core, spring.beans, spring.context;
}