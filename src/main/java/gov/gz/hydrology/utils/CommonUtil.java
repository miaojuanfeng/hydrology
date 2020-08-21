package gov.gz.hydrology.utils;

import gov.gz.hydrology.constant.NumberConst;
import gov.gz.hydrology.entity.write.Plan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * {{文件描述}}
 *
 * @author 缪隽峰
 * @version 1.0
 * @date 2020年08月17日
 */
public class CommonUtil {

    public static Plan plan = new Plan();

    public static List<BigDecimal> listP = new ArrayList<>();

    public static List<BigDecimal> listR = new ArrayList<>();

    public static BigDecimal diffVal(BigDecimal x, List<BigDecimal> X0, List<BigDecimal> Y0){
        BigDecimal tempX = x;
        Integer x_wz = -1;
        if( NumberUtil.le(tempX, X0.get(0)) ){
            tempX = X0.get(0);
            x_wz = 0;
        }
        if( NumberUtil.ge(tempX, X0.get(X0.size()-1)) ){
            tempX = X0.get(X0.size()-1);
            x_wz = X0.size()-1;
        }
        for (int i = 0; i < X0.size()-1; i++){
            if( NumberUtil.ge(tempX, X0.get(i)) && NumberUtil.le(tempX, X0.get(i+1)) ){
                x_wz = i;
                break;
            }
        }
        /**
         * chazhi = (x_tmp - X0(x_wz)) / (X0(x_wz + 1) - X0(x_wz)) * (Y0(x_wz + 1) - Y0(x_wz)) + Y0(x_wz)
         */
        BigDecimal temp1 = tempX.subtract(X0.get(x_wz));
        BigDecimal temp2 = X0.get(x_wz+1).subtract(X0.get(x_wz));
        BigDecimal temp3 = Y0.get(x_wz+1).subtract(Y0.get(x_wz));
        BigDecimal temp4 = Y0.get(x_wz);
        return temp1.divide(temp2, NumberConst.DIGIT, NumberConst.MODE).multiply(temp3).add(temp4);
    }
}
