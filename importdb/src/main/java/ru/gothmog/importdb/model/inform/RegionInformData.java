package ru.gothmog.importdb.model.inform;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegionInformData {
    private Long id;
    private String idLocalityRegion;
    private String idKeyInformName;
    private String reportingPeriod;
    private BigDecimal valueRegion;
    private String dynamics;
    private LocalDate publicationDate;
}
