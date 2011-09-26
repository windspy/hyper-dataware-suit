package org.windspy.hyperdw;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: yibing.tan
 * Date: 11-9-19
 * Time: 下午4:41
 * To change this template use File | Settings | File Templates.
 */
public class LineRecord {
    private String lineId;
    private Map<String,String> recordInfos;
    private LineRecord(){};
    public LineRecord(String lineId,Map<String,String> recordInfos){
        this.recordInfos = recordInfos;
        this.lineId = lineId;
    }

    public String getLineId() {
        return lineId;
    }

    public Map<String, String> getRecordInfos() {
        return recordInfos;
    }
}
