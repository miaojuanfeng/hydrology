package gov.gz.hydrology.entity.read;

import java.math.BigDecimal;
import java.util.Date;

public class Zq {
    private String stcd;

    private BigDecimal z;

    private BigDecimal q;

    public String getStcd() {
        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    public BigDecimal getZ() {
        return z;
    }

    public void setZ(BigDecimal z) {
        this.z = z;
    }

    public BigDecimal getQ() {
        return q;
    }

    public void setQ(BigDecimal q) {
        this.q = q;
    }
}
