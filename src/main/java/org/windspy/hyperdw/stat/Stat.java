package org.windspy.hyperdw.stat;

/**
 * Created by IntelliJ IDEA.
 * User: yibing.tan
 * Date: 11-9-19
 * Time: 下午7:26
 * To change this template use File | Settings | File Templates.
 */
public class Stat {
    StatHint hint;
    String hintOb;
    public Stat(StatHint hint, String hintOb){
        this.hint = hint;
        this.hintOb = hintOb;
    }
    public StatHint getHint() {
        return hint;
    }

    public String getHintOb() {
        return hintOb;
    }
}
