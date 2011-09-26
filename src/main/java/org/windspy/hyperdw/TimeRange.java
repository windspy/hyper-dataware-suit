package org.windspy.hyperdw;

import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 * User: yibing.tan
 * Date: 11-9-21
 * Time: 上午11:10
 * To change this template use File | Settings | File Templates.
 */
public class TimeRange {
    private long startTime;
    private long endTime;

    private TimeRange(){}

    public TimeRange(long startTime, long endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public String toString(){
        return startTime+"-"+endTime;
    }
}
