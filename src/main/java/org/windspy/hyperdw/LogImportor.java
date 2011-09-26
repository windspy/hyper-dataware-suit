package org.windspy.hyperdw;

import org.windspy.hyperdw.stat.StatRunner;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yibing.tan
 * Date: 11-9-19
 * Time: 下午6:05
 * To change this template use File | Settings | File Templates.
 */
public class LogImportor {
    public void import2Hyper(List<InputStream> streamList, StatRunner runner) throws Exception{
        if (streamList==null||streamList.isEmpty()) {
            System.err.println("no log file need to import");
            return;
        }
        int i=0;
        double sum = streamList.size();
        for (InputStream curfs : streamList) {

            if (curfs == null) continue;
            new ImportRunner(curfs, runner).run();
            i++;
            double progress = i / sum * 100;
            double d = new BigDecimal(progress).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
            System.err.println("[Import]: complete " + d + "%");
        }
        System.err.println("import log complete");
        HyerTableDriver.updateTime(ApacheLogCollector.getYestodayDateStr());
    }
}
