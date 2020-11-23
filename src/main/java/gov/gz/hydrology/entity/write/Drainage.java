package gov.gz.hydrology.entity.write;

import java.math.BigDecimal;

/**
 *
 *
 *
 *
 *
 * 这张表的主键不能自增
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

/**
 * 泄流曲线
 *
 * @author 缪隽峰
 * @version 1.0
 * @date 2020年08月29日
 */
public class Drainage {

    private String stcd;

    private BigDecimal z0;

    private BigDecimal hcoq;

    public String getStcd() {
        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    public BigDecimal getZ0() {
        return z0;
    }

    public void setZ0(BigDecimal z0) {
        this.z0 = z0;
    }

    public BigDecimal getHcoq() {
        return hcoq;
    }

    public void setHcoq(BigDecimal hcoq) {
        this.hcoq = hcoq;
    }
}
