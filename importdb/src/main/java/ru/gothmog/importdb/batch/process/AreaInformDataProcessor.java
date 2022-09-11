package ru.gothmog.importdb.batch.process;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gothmog.importdb.model.inform.AreaInformData;
import ru.gothmog.importdb.service.ImportDataService;

import java.math.BigDecimal;

import static ru.gothmog.importdb.util.OperationWitchBigDecimal.calculateDoublePercentage;
import static ru.gothmog.importdb.util.OperationWitchBigDecimal.calculationPercentage;

public class AreaInformDataProcessor implements ItemProcessor<AreaInformData,AreaInformData> {

    @Autowired
    private ImportDataService importDataService;

    @Override
    public AreaInformData process(AreaInformData item) throws Exception {
        AreaInformData output = new AreaInformData();
        output.setIdLocalityArea(item.getIdLocalityArea());
        output.setIdKeyInformName(item.getIdKeyInformName());
        output.setValueArea(item.getValueArea());
        output.setReportingPeriod(item.getReportingPeriod());
        String idLocality = output.getIdLocalityArea();
        String idKeyInformName = output.getIdKeyInformName();
        String reportingPeriodNew = output.getReportingPeriod();
        Integer resultDataPeriodOld = Integer.valueOf(reportingPeriodNew) - 1;
        String reportingPeriodOld = String.valueOf(resultDataPeriodOld);
        BigDecimal newValue = output.getValueArea();
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
