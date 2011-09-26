package org.windspy.hyperdw.stat;

import org.windspy.hyperdw.LineRecord;
import org.windspy.hyperdw.LogEntry;
import org.windspy.hyperdw.stat.impl.LikedStatFactory;
import org.windspy.hyperdw.stat.impl.RetweetStatFactory;
import org.windspy.hyperdw.stat.impl.ViewStatFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: yibing.tan
 * Date: 11-9-20
 * Time: 上午10:16
 * To change this template use File | Settings | File Templates.
 */
public class StatRunner {
    List<StatFactory> highPrifactoryList = new LinkedList<StatFactory>();
    List<StatFactory> lowPrifactoryList = new LinkedList<StatFactory>();
    private void init(List<StatFactory> factories){
        if (factories==null||factories.isEmpty()) return;
        for (StatFactory currnt:factories)
            add2List(currnt);
    }
    private StatRunner(){

    }
    public StatRunner(List<StatFactory> factories){
         init(factories);
    }

    private void add2List(StatFactory factory) {
         if (factory.isLowPriority())
            lowPrifactoryList.add(factory);
        else
            highPrifactoryList.add(factory);
    }

    public LineRecord createStats(String lineRecordId, String action){
        LineRecord record = createStats(lineRecordId,action,highPrifactoryList);
        if (record!=null) return record;
        return createStats(lineRecordId,action,lowPrifactoryList);
    }

    private LineRecord createStats(String lineRecordId, String action,List<StatFactory> prifactoryList){
        Map<String, String> cols  = new HashMap<String, String>();
        for (StatFactory currentFactory: prifactoryList){
            Stat stat = currentFactory.create(lineRecordId, action);
            if (stat==null) continue;
            lineRecordId+= "-"+stat.getHint().getName();
            String targetId = stat.getHintOb();
            if (targetId!=null)
                 cols.put(LogEntry.actionTargetId.name(), targetId);
            return new LineRecord(lineRecordId, cols);
        }
        return null;
    }


}
