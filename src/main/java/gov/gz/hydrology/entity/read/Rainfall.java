package gov.gz.hydrology.entity.read;

import java.math.BigDecimal;

public class Rainfall {
    private String stcd;

    private BigDecimal rainfallTotal;

    public String getStcd() {
        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    public BigDecimal getRainfallTotal() {
        return rainfallTotal;
    }

    public void setRainfallTotal(BigDecimal rainfallTotal) {
        this.rainfallTotal = rainfallTotal;
    }
}
