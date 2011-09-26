package org.windspy.hyperdw;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: yibing.tan
 * Date: 11-9-21
 * Time: 上午11:58
 * To change this template use File | Settings | File Templates.
 */
public class DateForm {
    private static final String FOMR = "yyyy-MM-dd HH:mm:ss";
    final static SimpleDateFormat sm = new SimpleDateFormat(FOMR);

    public static String getDateStr(long time){
         return sm.format(new Date(time));
    }
}
