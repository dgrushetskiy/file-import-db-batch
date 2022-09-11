package ru.gothmog.importdb.batch.process;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gothmog.importdb.model.inform.RegionInformData;
import ru.gothmog.importdb.service.ImportDataService;

import java.math.BigDecimal;

import static ru.gothmog.importdb.util.OperationWitchBigDecimal.calculateDoublePercentage;
import static ru.gothmog.importdb.util.OperationWitchBigDecimal.calculationPercentage;

public class RegionInformDataProcessor implements ItemProcessor<RegionInformData,RegionInformData> {

    @Autowired
    private ImportDataService importDataService;

    @Override
    public RegionInformData process(RegionInformData item) throws Exception {
       RegionInformData output = new RegionInformData();
        output.setIdLocalityRegion(item.getIdLocalityRegion());
        output.setIdKeyInformName(item.getIdKeyInformName());
        output.setValueRegion(item.getValueRegion());
        output.setReportingPeriod(item.getReportingPeriod());
        String idLocality = output.getIdLocalityRegion();
        String idKeyInformName = output.getIdKeyInformName();
        String reportingPeriodNew = output.getReportingPeriod();
        Integer resultDataPeriodOld = Integer.valueOf(reportingPeriodNew) - 1;
        String reportingPeriodOld = String.valueOf(resultDataPeriodOld);
        BigDecimal newValue = output.getValueRegion();
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
