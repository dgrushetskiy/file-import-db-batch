package ru.gothmog.importdb.batch.process;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gothmog.importdb.model.inform.CityInformData;
import ru.gothmog.importdb.service.ImportDataService;

import java.math.BigDecimal;

import static ru.gothmog.importdb.util.OperationWitchBigDecimal.calculateDoublePercentage;
import static ru.gothmog.importdb.util.OperationWitchBigDecimal.calculationPercentage;

public class CityInformDataProcessor implements ItemProcessor<CityInformData,CityInformData> {
    @Autowired
    private ImportDataService importDataService;

    @Override
    public CityInformData process(CityInformData item) throws Exception {
        CityInformData output = new CityInformData();
        output.setIdLocalityCity(item.getIdLocalityCity());
        output.setIdKeyInformName(item.getIdKeyInformName());
        output.setValueCity(item.getValueCity());
        output.setReportingPeriod(item.getReportingPeriod());
        String idLocality = output.getIdLocalityCity();
        String idKeyInformName = output.getIdKeyInformName();
        String reportingPeriodNew = output.getReportingPeriod();
        Integer resultDataPeriodOld = Integer.valueOf(reportingPeriodNew) - 1;
        String reportingPeriodOld = String.valueOf(resultDataPeriodOld);
        BigDecimal newValue = output.getValueCity();
        BigDecimal oldValue = importDataService.valueData(idLocality,idKeyInformName,reportingPeriodOld);
        if (oldValue!=null && oldValue!=BigDecimal.ZERO){
            Double calculationProcent = calculateDoublePercentage(newValue,oldValue);
            String formattedDouble = String.format("%.1f", calculationProcent);
            String resultProcent = formattedDouble + " %";
            output.setDynamics(resultProcent);
        }else {
            Double calculationProcent = Double.valueOf(0);
            String formattedDouble = String.format("%.1f", calculationProcent);
            String resultProcent = formattedDouble + " %";
            output.setDynamics(resultProcent);
        }
        return output;
    }
}
