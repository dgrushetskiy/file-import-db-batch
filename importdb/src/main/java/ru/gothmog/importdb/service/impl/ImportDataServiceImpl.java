package ru.gothmog.importdb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gothmog.importdb.dao.ImportDataDao;
import ru.gothmog.importdb.service.ImportDataService;

import java.math.BigDecimal;

@Service("mportDataService")
public class ImportDataServiceImpl implements ImportDataService {

    private ImportDataDao importDataDao;
    @Autowired
    public ImportDataServiceImpl(ImportDataDao importDataDao) {
        this.importDataDao = importDataDao;
    }

    @Override
    public BigDecimal valueData(String idLocality, String idKeyInformName, String reportingPeriod) {
        return importDataDao.valueData(idLocality, idKeyInformName, reportingPeriod);
    }
}
