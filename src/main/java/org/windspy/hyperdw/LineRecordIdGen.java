package org.windspy.hyperdw;

/**
 * Created by IntelliJ IDEA.
 * User: yibing.tan
 * Date: 11-9-20
 * Time: 上午10:33
 * To change this template use File | Settings | File Templates.
 */
public class LineRecordIdGen {
    private static final String CONN = "-" ;
    public static String genId(String client_type, String deviceId, long dis_time) {
        return client_type + CONN + deviceId + CONN + dis_time;
    }

    public static String getProduct(String genId) {
        int f = genId.indexOf(CONN);
        return genId.substring(0, f);
    }

    public static String getDeviceId(String genId) {
        int s = genId.indexOf(CONN);
        int f = genId.lastIndexOf(CONN);
        genId = genId.substring(0, f);
        f = genId.lastIndexOf(CONN);
        return genId.substring(s + 1, f);
    }

    public static String getActionTime(String genId) {
        int f = genId.lastIndexOf(CONN);
        genId = genId.substring(0, f);
        f = genId.lastIndexOf(CONN);
        return genId.substring(f + 1, genId.length());
    }

    public static String getActionHint(String genId) {
        int f = genId.lastIndexOf(CONN);
        return genId.substring(f+1, genId.length());
    }
}
