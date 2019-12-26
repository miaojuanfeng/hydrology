package gov.gz.hydrology.entity.read;

import java.math.BigDecimal;
import java.util.Date;

public class River {
    private String stcd;

    private Date tm;

    private BigDecimal z;

    private BigDecimal q;

    private BigDecimal x1;

    private BigDecimal y1;

    private BigDecimal x2;

    private BigDecimal y2;

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


    public BigDecimal getX1() {
        return x1;
    }

    public void setX1(BigDecimal x1) {
        this.x1 = x1;
    }

    public BigDecimal getY1() {
        return y1;
    }

    public void setY1(BigDecimal y1) {
        this.y1 = y1;
    }

    public BigDecimal getX2() {
        return x2;
    }

    public void setX2(BigDecimal x2) {
        this.x2 = x2;
    }

    public BigDecimal getY2() {
        return y2;
    }

    public void setY2(BigDecimal y2) {
        this.y2 = y2;
    }
}
