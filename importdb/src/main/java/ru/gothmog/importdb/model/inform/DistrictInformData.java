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
public class DistrictInformData {
    private Long id;
    private String idLocalityDistrict;
    private String idKeyInformName;
    private String reportingPeriod;
    private BigDecimal valueDistrict;
    private String dynamics;
    private LocalDate publicationDate;
}
