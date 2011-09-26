package org.windspy.hyperdw.stat;

import org.windspy.hyperdw.stat.impl.LikedStatFactory;
import org.windspy.hyperdw.stat.impl.RetweetStatFactory;
import org.windspy.hyperdw.stat.impl.ViewStatFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yibing.tan
 * Date: 11-9-19
 * Time: 下午7:25
 * To change this template use File | Settings | File Templates.
 */
public abstract class StatFactory {
    public abstract Stat create(String statId, String action);

    public abstract boolean isLowPriority();

    public static List<StatFactory> getDefaultFactories() {
        List<StatFactory> result = new LinkedList<StatFactory>();
        result.add(new LikedStatFactory());
        result.add(new RetweetStatFactory());
        result.add(new ViewStatFactory());
        return result;
    }
}
