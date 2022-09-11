package ru.gothmog.importdb.service;

import java.math.BigDecimal;

public interface ImportDataService {

    BigDecimal valueData(String idLocality, String idKeyInformName, String reportingPeriod);
}
