package ru.gothmog.importdb.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class ImportData {
    private Long id;
    private String idLocality;
    private String idKeyInformName;
    private BigDecimal valueAll;
    private String reportingPeriod;

}
