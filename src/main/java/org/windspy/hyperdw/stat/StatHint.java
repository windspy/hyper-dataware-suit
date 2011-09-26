package org.windspy.hyperdw.stat;

import org.windspy.hyperdw.stat.hint.ClickStatHint;
import org.windspy.hyperdw.stat.hint.LikeStatHint;
import org.windspy.hyperdw.stat.hint.RetweetStatHint;
import org.windspy.hyperdw.stat.hint.ViewStatHint;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yibing.tan
 * Date: 11-9-19
 * Time: 下午7:25
 * To change this template use File | Settings | File Templates.
 */
public abstract class StatHint {
    public abstract String getName();

    public static StatHint[] getDefaultHints(){
        List<StatHint> list = new LinkedList<StatHint>();
        list.add(new ClickStatHint());
        list.add(new LikeStatHint());
        list.add(new RetweetStatHint());
        list.add(new ViewStatHint());
        return list.toArray(new StatHint[0]);
    }
}
