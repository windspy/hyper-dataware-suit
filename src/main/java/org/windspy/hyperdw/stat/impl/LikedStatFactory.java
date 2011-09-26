package org.windspy.hyperdw.stat.impl;

import org.windspy.hyperdw.stat.Stat;
import org.windspy.hyperdw.stat.StatFactory;
import org.windspy.hyperdw.stat.hint.LikeStatHint;
import org.windspy.hyperdw.util.ConfigUtils;

/**
 * Created by IntelliJ IDEA.
 * User: yibing.tan
 * Date: 11-9-20
 * Time: 上午10:24
 * To change this template use File | Settings | File Templates.
 */
public class LikedStatFactory extends StatFactory{
    public static final String LIKED_HINT = ConfigUtils.getProperty("LIKED_HINT","/api/like?");
    public static final String LIKED_ID_HINT = ConfigUtils.getProperty("LIKED_ID_HINT","Id=");

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
            if (action.indexOf(LIKED_HINT)>-1) return null;
            int start = action.indexOf(LIKED_ID_HINT);
            int end = action.lastIndexOf(" ");
            articleId = action.substring(start+LIKED_ID_HINT.length(),end);
        }else
            return null;
        return new Stat(new LikeStatHint(), articleId);
    }
}
