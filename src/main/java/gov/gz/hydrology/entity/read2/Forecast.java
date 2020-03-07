package gov.gz.hydrology.entity.read2;

import java.math.BigDecimal;
import java.util.Date;

public class Forecast {

    private Integer id;

    private Date ymdh;

    private BigDecimal rn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getYmdh() {
        return ymdh;
    }

    public void setYmdh(Date ymdh) {
        this.ymdh = ymdh;
    }

    public BigDecimal getRn() {
        return rn;
    }

    public void setRn(BigDecimal rn) {
        this.rn = rn;
    }
}
