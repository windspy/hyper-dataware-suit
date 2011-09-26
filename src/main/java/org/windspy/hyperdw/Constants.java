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
    public static final String DATE_FOMATOR = ConfigUtils.getProperty("DATE_FOMATOR","dd/MMM/yyyy:HH:mm:ss Z");
    public static final String[] productarr = ConfigUtils.getProperty("productarr", "product1,product2").split(",");

    public static final String LogFilePattern = ConfigUtils.getProperty("LogFilePattern","access.log");
    public static final String dirpath = ConfigUtils.getProperty("dirpath", "/var/httpd/log");
    public static final String logSurfixPatter = ConfigUtils.getProperty("logSurfixPatter","yyyy-MM-dd");
    public static final String ns = ConfigUtils.getProperty("ns","hyper-dataware");
    public static final String host = ConfigUtils.getProperty("hyper_host","localhost");
    public static final String db_table = ConfigUtils.getProperty("hyper_db_table","actionlog");
    public static final int port = ConfigUtils.getProperty("hyper_port",38080);

    public static final int NUM_FIELDS = ConfigUtils.getProperty("NUM_FIELDS",9);
    public static final String logEntryPattern = ConfigUtils.getProperty("logEntryPattern","^([\\d.]+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(.+?)\" (\\d{3}) (\\d+) \"([^\"]+)\" \"([^\"]+)\"");

}
