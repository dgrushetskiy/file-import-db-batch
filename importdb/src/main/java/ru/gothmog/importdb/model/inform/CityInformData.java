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
public class CityInformData {
    private Long id;
    private String idLocalityCity;
    private String idKeyInformName;
    private BigDecimal valueCity;
    private String reportingPeriod;
    private String dynamics;
    private LocalDate publicationDate;
}
