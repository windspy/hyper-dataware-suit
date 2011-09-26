package org.windspy.hyperdw;

import org.hypertable.thrift.ThriftClient;
import org.hypertable.thriftgen.Cell;
import org.hypertable.thriftgen.HqlResult;
import java.util.List;
import java.util.Map;

public class HyerTableDriver {
    public static String getLast(){
        HqlResult result = execute("select * from lastupdate");
        List<Cell> r = result.getCells();
        for (Cell c:r)
            return new String(c.getValue());
        return  null;
    }

    public static void updateTime(String time){
        execute("DELETE * FROM lastupdate WHERE ROW=\"1\"");
        execute("insert into lastupdate values(\"1\",\"updatetime\",\""+time+"\")");
    }
    public static void insertRecord(List<LineRecord> lineRecords) {
        String sql = "insert into "+Constants.db_table+" values";
        if (lineRecords==null||lineRecords.isEmpty()) return;
        boolean added = false;
        for (LineRecord currentLine : lineRecords) {
            Map<String, String> recordInfos = currentLine.getRecordInfos();
            if (recordInfos==null||recordInfos.isEmpty()) continue;
            String rid = currentLine.getLineId();
            for (String key : recordInfos.keySet()){
                sql += "(\"" + rid + "\",\"" + key + "\",\"" + recordInfos.get(key) + "\"),";
                if (!added)
                    added = true;
            }
        }
        if (!added) return;
        sql = sql.substring(0, sql.length() - 1);
        execute(sql);
    }

    public static HqlResult execute(String command) {
        long namespace = 0;
        ThriftClient client = null;
        try {
            client = ThriftClient.create(Constants.host, Constants.port);
            namespace = client.open_namespace(Constants.ns);
            HqlResult result = client.hql_query(namespace, command);
            return result;
        } catch (Exception e) {
            System.err.println("command error!"+command);
        } finally {
            if (client != null)
                try {
                    client.close_namespace(namespace);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return null;
    }
}
