package ru.gothmog.importdb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class CustomBatchConfigurer {
    @Autowired
    @Qualifier("dataSource")
    public DataSource dataSource;

}
