package ru.gothmog.importdb.dao;

import java.math.BigDecimal;

public interface ImportDataDao {

    BigDecimal valueData(String idLocality,String idKeyInformName,String reportingPeriod);
}
