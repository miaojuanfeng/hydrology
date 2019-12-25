package gov.gz.hydrology.entity.read;

import java.math.BigDecimal;
import java.util.Date;

public class River {
    private String stcd;

    private Date tm;

    private BigDecimal z;

    private BigDecimal q;

    private BigDecimal minZ;

    private BigDecimal maxZ;

    public String getStcd() {
        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    public Date getTm() {
        return tm;
    }

    public void setTm(Date tm) {
        this.tm = tm;
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


    public BigDecimal getMinZ() {
        return minZ;
    }

    public void setMinZ(BigDecimal minZ) {
        this.minZ = minZ;
    }

    public BigDecimal getMaxZ() {
        return maxZ;
    }

    public void setMaxZ(BigDecimal maxZ) {
        this.maxZ = maxZ;
    }
}
