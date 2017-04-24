package com.mmp.cq.weixin.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mmp.cq.utils.XmlUtils;
import com.mmp.cq.weixin.message.MessageTypes.ResponseTypes;
import com.mmp.cq.weixin.message.response.Article;
import com.mmp.cq.weixin.message.response.NewsMessage;
import com.mmp.cq.weixin.message.response.TextMessage;

public class ResponseUtils {

    public static String responsejsDemoNewsMessage(String toUserName ,String fromUserName){
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setArticleCount(1);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setFromUserName(toUserName);
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFuncFlag(1);
        newsMessage.setMsgType(ResponseTypes.NEWS);
        List l = new ArrayList<>();
        Article a = new Article();
        a.setDescription("微信jsdemo");
        a.setTitle("微信jsdemo");
        a.setUrl("http://203.195.235.76/jssdk/");
        l.add(a);
        newsMessage.setArticles(l);
        Map m = new HashMap();
        m.put("item", Article.class);
        return XmlUtils.toXML(newsMessage,m);
    }
        
    public static String responseText(com.mmp.cq.weixin.message.request.BaseMessage baseMessage,String responseText){
        TextMessage text = new TextMessage();
        text.setCreateTime(System.currentTimeMillis());
        text.setMsgType(ResponseTypes.TEXT);
        text.setContent(responseText);
        text.setFuncFlag(0);
        text.setFromUserName(baseMessage.getToUserName());
        text.setToUserName(baseMessage.getFromUserName());
        return XmlUtils.toXML(text, null);
    }
    
    public static String responseDefaultText(com.mmp.cq.weixin.message.request.BaseMessage baseMessage){
        TextMessage text = new TextMessage();
        text.setCreateTime(System.currentTimeMillis());
        text.setMsgType(ResponseTypes.TEXT);
        text.setContent("sucess");
        text.setFuncFlag(0);
        text.setFromUserName(baseMessage.getToUserName());
        text.setToUserName(baseMessage.getFromUserName());
        return XmlUtils.toXML(text, null);
    }
    public static void main(String[] args) {
      System.out.println(responsejsDemoNewsMessage("a","b"));
    }
}
