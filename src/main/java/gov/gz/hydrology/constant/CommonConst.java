package gov.gz.hydrology.constant;

import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
}
