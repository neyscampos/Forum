use titioavo;

show tables;


desc assunto;
desc resposta;

select * from assunto;
select * from resposta;


----------------
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC "-//Hibernate/Hibernate Configuration DTD 3.0//EN" "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
  <session-factory>
    <property name="hibernate.dialect">org.hibernate.dialect.MySQLDialect</property>
    <property name="hibernate.connection.driver_class">com.mysql.jdbc.Driver</property>
    <property name="hibernate.connection.url">
jdbc:mysql://node154464-neyforum.jelasticlw.com.br:3306/titioavo</property>
    <property name="hibernate.connection.username">root</property>
    <property name="hibernate.connection.password">EBLhns71781</property>

    <property name="hibernate.show_sql">true</property>
    <property name="hibernate.format_sql">true</property>
    <property name="hibernate.hbm2ddl.auto">update</property>
    
        <mapping class="entity.Assunto"/>
        <mapping class="entity.Resposta"/>


 </session-factory>
</hibernate-configuration>
