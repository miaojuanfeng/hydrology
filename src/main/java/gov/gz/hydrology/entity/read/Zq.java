package gov.gz.hydrology.entity.read;

import java.math.BigDecimal;
import java.util.Date;

public class Zq {
    private String stcd;

    private BigDecimal x;

    private BigDecimal y;

    public String getStcd() {
        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }


    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }
}
