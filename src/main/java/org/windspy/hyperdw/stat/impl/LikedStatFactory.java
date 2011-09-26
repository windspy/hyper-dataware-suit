package org.windspy.hyperdw.stat.impl;

import org.windspy.hyperdw.stat.Stat;
import org.windspy.hyperdw.stat.StatFactory;
import org.windspy.hyperdw.stat.StatHint;
import org.windspy.hyperdw.stat.hint.LikeStatHint;

/**
 * Created by IntelliJ IDEA.
 * User: yibing.tan
 * Date: 11-9-20
 * Time: 上午10:24
 * To change this template use File | Settings | File Templates.
 */
public class LikedStatFactory extends StatFactory{
    public static final String LIKED_HINT = "/api/article/like?";
    public static final String LIKED_ARTICLEID_HINT = "articleId=";

    @Override
    public Stat create(String statId, String action) {
        if (action==null) return null;
        Stat stat = checkLikedArticle(statId, action);
        return stat;
    }

    @Override
    public boolean isLowPriority() {
        return false;
    }

    private Stat checkLikedArticle(String statId ,String action) {
         String articleId = null;
        int share_index = action.indexOf(LIKED_HINT);
        if ( share_index > -1) {
            if (action.indexOf(LIKED_HINT+"type=1")>-1) return null;
            int start = action.indexOf(LIKED_ARTICLEID_HINT);
            int end = action.lastIndexOf(" ");
            articleId = action.substring(start+LIKED_ARTICLEID_HINT.length(),end);
        }else
            return null;
        return new Stat(new LikeStatHint(), articleId);
    }
}
