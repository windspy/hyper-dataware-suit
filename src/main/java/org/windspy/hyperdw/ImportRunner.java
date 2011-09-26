package org.windspy.hyperdw;

import org.windspy.hyperdw.stat.StatRunner;
import org.windspy.hyperdw.util.LogRegExp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;

/**
 * Created by IntelliJ IDEA.
 * User: yibing.tan
 * Date: 11-9-19
 * Time: 下午6:18
 * To change this template use File | Settings | File Templates.
 */
public class ImportRunner implements Runnable {

    static final int BATCH_SIZE = 15;
    private InputStream curfs;
    private StatRunner runner;

    public ImportRunner(InputStream curfs, StatRunner runner) {
        this.curfs = curfs;
        this.runner = runner;
    }

    @Override
    public void run() {
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(curfs));
            String line = null;
            List<LineRecord> records = new LinkedList<LineRecord>();
            LineRecord record = null;
            try {
                while ((line = bf.readLine()) != null) {
                    try {
                        record = handleLine(line);
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }
                    if (record == null) continue;
                    records.add(record);
                    if (records.size() > BATCH_SIZE) {
                        HyerTableDriver.insertRecord(records);
                        records = new LinkedList<LineRecord>();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            curfs.close();
            bf.close();
            if (!records.isEmpty())
                HyerTableDriver.insertRecord(records);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private LineRecord handleLine(String line) {

        if (line == null || "".equals(line.trim())) return null;

        LogRegExp regExp = new LogRegExp(line);
        Matcher matcher = regExp.parse();

        if (matcher == null) return null;

        String client_type = matcher.group(2);

        if (client_type == null || "".equalsIgnoreCase(client_type.trim())) return null;

        String deviceId = matcher.group(3);

        if (deviceId == null || "".equalsIgnoreCase(deviceId.trim()))
            return null;

        String action_time = matcher.group(4);

        if (action_time == null || "".equalsIgnoreCase(action_time.trim())) return null;
        long dis_time = 0;
        try {
            DateFormat dateTimeFormat = new SimpleDateFormat(Constants.DATE_FOMATOR, Locale.ENGLISH);
            dis_time = dateTimeFormat.parse(action_time).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dis_time <= 0) return null;

        String action = matcher.group(5);
        if (action == null || action.equalsIgnoreCase("")) return null;
        String lineRecordId = LineRecordIdGen.genId(client_type, deviceId, dis_time);
        return runner.createStats(lineRecordId, action);
    }
}
