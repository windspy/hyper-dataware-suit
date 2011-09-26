package org.windspy.hyperdw;

import org.hypertable.thriftgen.Cell;
import org.hypertable.thriftgen.HqlResult;
import org.hypertable.thriftgen.Key;
import org.windspy.hyperdw.cache.CacheService;
import org.windspy.hyperdw.stat.StatHint;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yibing.tan
 * Date: 11-9-15
 * Time: 上午11:42
 * To change this template use File | Settings | File Templates.
 */
public class LogDBViewer {

    public static CacheService cacheService = new CacheService();

    public static List<InputStream> getLogFiles(String product, StatHint[] hints, TimeRange timeRange) throws Exception {
        List<InputStream> inputStreams = new LinkedList<InputStream>();
        for (StatHint hint : hints) {
            InputStream ins = getLogFiles(product, hint, timeRange);
            if (ins != null)
                inputStreams.add(ins);
        }
        return inputStreams;
    }

    public static InputStream getLogFiles(String product, StatHint statHint, TimeRange timeRange) throws Exception {
        String keycache = product + "-" + statHint.getName();
        if (timeRange != null)
            keycache += "-" + timeRange.toString();
        String content = (String) cacheService.get(keycache);
        if (content != null)
            return new ByteArrayInputStream(content.getBytes());
        String command = "select * from " + Constants.db_table;

        if (product != null || statHint != null) {
            command += " where ROW REGEXP \"";
            if (product != null)
                command += "^" + product + "-";
            if (statHint == null)
                command += "*\"";
            else
                command += "\\S+" + statHint.getName() + "\"";
            if (timeRange != null)
                command += " and ";
        }
        if (timeRange != null)
            command += "'" + DateForm.getDateStr(timeRange.getStartTime()) + "' <= TIMESTAMP < '" + DateForm.getDateStr(timeRange.getEndTime()) + "'";
        HqlResult result = HyerTableDriver.execute(command);
        if (result == null) return null;
        List<Cell> cells = result.getCells();
        if (cells == null || cells.isEmpty()) return null;
        String id = "";
        Key key = null;
        String val = "";
        StringBuffer sb = new StringBuffer();
        for (Cell cel : cells) {
            key = cel.getKey();
            if (key == null) continue;
            id = key.getRow();
            val = new String(cel.getValue());
            sb.append(id + "\t" + val + "\n");
        }
        cacheService.safeAdd(keycache, sb.toString(), 1200);
        return new ByteArrayInputStream(sb.toString().getBytes());
    }

    public static List<InputStream> getAllLogFiles(StatHint[] hints, TimeRange timeRange) throws Exception {
        List<InputStream> inputStreams = new LinkedList<InputStream>();
        for (String product : Constants.productarr)
            for (StatHint hint : hints) {
                InputStream ins = getLogFiles(product, hint, timeRange);
                if (ins != null)
                    inputStreams.add(ins);
            }
        return inputStreams;
    }

    public static List<InputStream> getLogFilesByProduct(StatHint statHint, TimeRange timeRange) throws Exception {
        List<InputStream> inputStreams = new LinkedList<InputStream>();
        for (String product : Constants.productarr) {
            InputStream ins = getLogFiles(product, statHint, timeRange);
            if (ins != null)
                inputStreams.add(ins);
        }
        return inputStreams;
    }
}
