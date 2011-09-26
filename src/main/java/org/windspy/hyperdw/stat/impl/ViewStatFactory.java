package org.windspy.hyperdw.stat.impl;

import org.windspy.hyperdw.LineRecordIdGen;
import org.windspy.hyperdw.stat.Stat;
import org.windspy.hyperdw.stat.StatFactory;
import org.windspy.hyperdw.stat.hint.ClickStatHint;
import org.windspy.hyperdw.stat.hint.ViewStatHint;

/**
 * Created by IntelliJ IDEA.
 * User: yibing.tan
 * Date: 11-9-20
 * Time: 上午10:24
 * To change this template use File | Settings | File Templates.
 */
public class ViewStatFactory extends StatFactory{
    public static final String IPAD_CLICK_Article_HINT = "detail?articleId=";
    public static final String CLICK_Article_HINT = "/api/article?articleId=";
    public static final String VIEW_Channel_HINT = "/api/channel/articles?channelId=";
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
        String product = LineRecordIdGen.getProduct(statId);
        if (product.equals("ipad")&&action.indexOf(IPAD_CLICK_Article_HINT) > -1)
            click_hint = IPAD_CLICK_Article_HINT;
        else if (!product.equals("ipad")&&action.indexOf(CLICK_Article_HINT) > -1)
            click_hint = CLICK_Article_HINT;
        else
            return null;
        int start = action.indexOf(click_hint) + click_hint.length();
        int end = action.lastIndexOf(" ");
        String articleId = action.substring(start,end);
        return new Stat(new ClickStatHint(), articleId);
    }

    private Stat checkViewChannel(String action) {
        String hint = VIEW_Channel_HINT;
        if (action.indexOf(hint)==-1) return null;
        int start = action.indexOf(hint) + hint.length();
        int end = action.lastIndexOf(" ");
        String channelId = action.substring(start,end);
        return new Stat(new ViewStatHint(), channelId);
    }
}
