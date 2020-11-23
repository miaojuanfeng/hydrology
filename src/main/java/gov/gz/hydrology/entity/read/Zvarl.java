package gov.gz.hydrology.entity.read;

import java.math.BigDecimal;

/**
 * 库容曲线
 *
 * @author 缪隽峰
 * @version 1.0
 * @date 2020年08月29日
 */
public class Zvarl {

    private String stcd;

    private String mstm;

    private BigDecimal rz;

    private BigDecimal w;

    public String getStcd() {
        return stcd;
    }

    public void setStcd(String stcd) {
        this.stcd = stcd;
    }

    public String getMstm() {
        return mstm;
    }

    public void setMstm(String mstm) {
        this.mstm = mstm;
    }

    public BigDecimal getRz() {
        return rz;
    }

    public void setRz(BigDecimal rz) {
        this.rz = rz;
    }

    public BigDecimal getW() {
        return w;
    }

    public void setW(BigDecimal w) {
        this.w = w;
    }
}
