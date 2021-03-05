package gov.gz.hydrology.constant;

import org.springframework.context.ApplicationContext;

public class CommonConst {
    public static final int HTTP_OK = 200;

    public static ApplicationContext APPLICATION_CONTEXT = null;

    public static final String[] STCD_STATION = {"62303350", "62303500", "62303650"};
    public static final String STCD_NINGDU = "62303350";
    public static final String STCD_FENKENG = "62303500";
    public static final String STCD_SHICHENG = "62303650";

    public static final String SESSION_KEY_USER = "user";
    public static final String SESSION_URL_CLASS = "urlClass";
    public static final String SESSION_URL_FUNCTION = "urlFunction";

    public static final Integer TYPE_JB_LINE = 1;
    public static final Integer TYPE_JJ_LINE = 2;







    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final Integer FUTURE_RAINFALL_MEASURE = 0;
    public static final Integer FUTURE_RAINFALL_JAPAN = 2;
    public static final Integer FUTURE_RAINFALL_EUROPE = 6;
    public static final Integer FORECAST_LIULIANG = 1;
    public static final Integer FORECAST_SHUIWEI = 2;
    public static final Integer RETURN_TYPE_R = 1;
    public static final Integer RETURN_TYPE_QTR = 2;
}
