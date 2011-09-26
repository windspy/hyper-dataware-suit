package org.windspy.hyperdw;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yibing.tan
 * Date: 11-9-15
 * Time: 上午11:42
 * To change this template use File | Settings | File Templates.
 */
public class ApacheLogCollector {

    static List<InputStream> getLogFiles() throws Exception{

        File dir = new File(Constants.dirpath);
        String[] names = dir.list();
        if (names==null||names.length==0) return null;
        List<InputStream> ins = new LinkedList<InputStream>();
        for (String cu: names){
            if (cu==null||!cu.startsWith(Constants.LogFilePattern)) continue;
            InputStream is = FileUtils.openInputStream(new File(Constants.dirpath + cu));
            ins.add(is);
        }
        return ins;
    }

    public static List<InputStream> getYestodayLogFile() throws Exception{
        String d = getYestodayDateStr();
        return getLogFileByDayStr(d);
    }

    private static List<InputStream> getLogFileByDayStr(String daystr) throws Exception{
        File dir = new File(Constants.dirpath);
        String[] names = dir.list();
        if (names==null||names.length==0) return null;
        List<InputStream> ins = new LinkedList<InputStream>();
        String r = HyerTableDriver.getLast();
        SimpleDateFormat sd = new SimpleDateFormat(Constants.logSurfixPatter);
        if (r==null||!sd.parse(r).before(sd.parse(daystr))) return null;
        File file = new File(Constants.dirpath+Constants.LogFilePattern+"."+daystr);
        if (file.exists()){
            InputStream is = FileUtils.openInputStream(file);
            ins.add(is);
            return ins;
        }
        System.err.println("no file of "+daystr+" exist!");
        return null;
    }

    public static List<InputStream> getDayLogFiles(String[] daystr) throws Exception{
        List<InputStream> result = new LinkedList<InputStream>();
        if (daystr==null||daystr.length==0) return result;
        for (String cuday: daystr)
        {
            List<InputStream> td = getLogFileByDayStr(cuday);
            if (td==null||td.isEmpty()) continue;
            result.addAll(td);
        }
        return result;
    }

    public static String getYestodayDateStr(){
        Date date = new Date();
        long time = date.getTime()-24*60*60*1000;
        date = new Date(time);
        return new SimpleDateFormat(Constants.logSurfixPatter).format(date);
    }
}
