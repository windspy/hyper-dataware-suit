package org.windspy.hyperdw.stat.impl;

import org.windspy.hyperdw.stat.Stat;
import org.windspy.hyperdw.stat.StatFactory;
import org.windspy.hyperdw.stat.hint.RetweetStatHint;

/**
 * Created by IntelliJ IDEA.
 * User: yibing.tan
 * Date: 11-9-20
 * Time: 上午10:24
 * To change this template use File | Settings | File Templates.
 */
public class RetweetStatFactory extends StatFactory{
    public static final String SHARE_HINT = "/api/share/article?";
    public static final String SHARE_ARTICLEID_HINT = "articleId=";

    public Stat create(String statId, String action) {
        if (action==null) return null;
        Stat stat = checkClickArticle(statId, action);
        return stat;
    }

    @Override
    public boolean isLowPriority() {
        return false;
    }

    private Stat checkClickArticle(String statId ,String action) {
        String articleId = "";

        int share_index = action.indexOf(SHARE_HINT);
        if ( share_index > -1) {
            int start = action.indexOf(SHARE_ARTICLEID_HINT);
            int end = action.lastIndexOf(" ");
            articleId = action.substring(start+SHARE_ARTICLEID_HINT.length(),end);
        }else
            return null;

        return new Stat(new RetweetStatHint(), articleId);
    }
}
