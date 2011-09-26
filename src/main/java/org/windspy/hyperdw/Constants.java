package org.windspy.hyperdw;

import org.windspy.hyperdw.util.ConfigUtils;

/**
 * Created by IntelliJ IDEA.
 * User: yibing.tan
 * Date: 11-9-20
 * Time: 下午5:29
 * To change this template use File | Settings | File Templates.
 */
public interface Constants {
    public static final String FILTER = "test";
    public static final String DATE_FOMATOR = "dd/MMM/yyyy:HH:mm:ss Z";
    public static final String[] productarr = ConfigUtils.getProperty("productarr", "1,3,4,5,ipad").split(",");

    public static final String LogFilePattern = "access.log";
    public static final String dirpath = ConfigUtils.getProperty("dirpath", "/var/neo/log/zhiyue/");
    public static final String logSurfixPatter = "yyyy-MM-dd";
    public static final String ns = "zhiyue";
    public static final String host = "10.219.8.51";
    public static final String db_table = "actionlog";
    public static final int port = 38080;

    public static final int NUM_FIELDS = 9;
    public static final String logEntryPattern = "^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"";

}
