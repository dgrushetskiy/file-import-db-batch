package ru.gothmog.importdb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.gothmog.importdb.dao.ImportDataDao;
import ru.gothmog.importdb.dao.impl.ImportDataDaoImpl;

@Configuration
public class AppConfig {
    @Bean
    public ImportDataDao importDataDao(){
        return new ImportDataDaoImpl();
    }
}
