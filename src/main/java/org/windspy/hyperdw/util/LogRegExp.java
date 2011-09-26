package org.windspy.hyperdw.util;

import org.windspy.hyperdw.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: yibing.tan
 * Date: 11-8-30
 * Time: 下午1:58
 * To change this template use File | Settings | File Templates.
 */
public class LogRegExp {

    public String logEntryLine;

    public LogRegExp(String logEntryLine){
         this.logEntryLine = logEntryLine;
    }

    public Matcher parse() {
        Pattern p = Pattern.compile(Constants.logEntryPattern);
        Matcher matcher = p.matcher(logEntryLine);
        if (!matcher.matches() || Constants.NUM_FIELDS != matcher.groupCount())
            return null;
        return matcher;
    }
}