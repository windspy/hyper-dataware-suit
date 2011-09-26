package org.windspy.hyperdw.stat.impl;

import org.windspy.hyperdw.stat.Stat;
import org.windspy.hyperdw.stat.StatFactory;
import org.windspy.hyperdw.stat.hint.ClickStatHint;
import org.windspy.hyperdw.stat.hint.ViewStatHint;
import org.windspy.hyperdw.util.ConfigUtils;

/**
 * Created by IntelliJ IDEA.
 * User: yibing.tan
 * Date: 11-9-20
 * Time: 上午10:24
 * To change this template use File | Settings | File Templates.
 */
public class ViewStatFactory extends StatFactory{
    public static final String CLICK_OB_HINT = ConfigUtils.getProperty("CLICK_OB_HINT", "/api/click/ob?");
    public static final String VIEW_OB_HINT = ConfigUtils.getProperty("VIEW_OB_HINT", "/api/view/ob?");
    @Override
    public Stat create(String statId, String action) {
        if (action==null) return null;
        Stat stat = checkClickArticle(statId, action);
        if (stat==null)
            stat = checkViewChannel(action);
        return stat;
    }

    @Override
    public boolean isLowPriority() {
        return false;
    }

    private Stat checkClickArticle(String statId ,String action) {
        String click_hint;
        if (action.indexOf(CLICK_OB_HINT) > -1)
            click_hint = CLICK_OB_HINT;
        else
            return null;
        int start = action.indexOf(click_hint) + click_hint.length();
        int end = action.lastIndexOf(" ");
        String articleId = action.substring(start,end);
        return new Stat(new ClickStatHint(), articleId);
    }

    private Stat checkViewChannel(String action) {
        String hint = VIEW_OB_HINT;
        if (action.indexOf(hint)==-1) return null;
        int start = action.indexOf(hint) + hint.length();
        int end = action.lastIndexOf(" ");
        String channelId = action.substring(start,end);
        return new Stat(new ViewStatHint(), channelId);
    }
}
