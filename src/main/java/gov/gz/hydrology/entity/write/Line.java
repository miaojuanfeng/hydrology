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
 * 单位线
 *
 * @author 缪隽峰
 * @version 1.0
 * @date 2020年08月29日
 */
public class Line {

    private String stcd;

    private BigDecimal f;

    public String getStcd() {
        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    public BigDecimal getF() {
        return f;
    }

    public void setF(BigDecimal f) {
        this.f = f;
    }
}
