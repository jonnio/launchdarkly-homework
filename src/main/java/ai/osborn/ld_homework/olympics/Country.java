package ai.osborn.ld_homework.olympics;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class Country {

    @Id
    private String countryName;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Integer population;

    @Column(nullable = false)
    private BigDecimal gdp;
}
