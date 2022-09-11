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
public class AreaInformData {
    private Long id;
    private String idLocalityArea;
    private String idKeyInformName;
    private String reportingPeriod;
    private BigDecimal valueArea;
    private String dynamics;
    private LocalDate publicationDate;
}
