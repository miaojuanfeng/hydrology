package gov.gz.hydrology.entity.write;

import java.math.BigDecimal;

public class CacheRiverTime {
    private String stcd;

    private String tm;

    private BigDecimal z;

    public String getStcd() {
        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    public String getTm() {
        return tm;
    }

    public void setTm(String tm) {
        this.tm = tm;
    }

    public BigDecimal getZ() {
        return z;
    }

    public void setZ(BigDecimal z) {
        this.z = z;
    }
}
