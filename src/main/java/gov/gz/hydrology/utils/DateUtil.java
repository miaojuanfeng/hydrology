package gov.gz.hydrology.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getDate() {
		return new SimpleDateFormat("YYYY年MM月dd日 - EEEE").format(new Date());
	}
}
