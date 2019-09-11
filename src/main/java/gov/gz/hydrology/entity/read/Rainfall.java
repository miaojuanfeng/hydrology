package gov.gz.hydrology.entity.read;

import java.math.BigDecimal;

public class Rainfall {
    private String stcd;

    private BigDecimal rainfall;

    public String getStcd() {
        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    public BigDecimal getRainfall() {
        return rainfall;
    }

    public void setRainfall(BigDecimal rainfall) {
        this.rainfall = rainfall;
    }
}
