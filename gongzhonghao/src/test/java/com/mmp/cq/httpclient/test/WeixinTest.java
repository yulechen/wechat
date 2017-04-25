package com.mmp.cq.httpclient.test;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

import com.mmp.cq.utils.ApiUtils;

public class WeixinTest {
    
    @Test
    public void testTextMesage(){
        String data= "<xml><ToUserName><![CDATA[gh_556ec53494bb]]></ToUserName><FromUserName><![CDATA[oCumrs8ghvroQqRk6bN-PKDvChrI]]></FromUserName><CreateTime>1493088221</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[weijsdemo]]></Content><MsgId>6412765079650479343</MsgId></xml>";
        try {
            String post = ApiUtils.post(new URI("http://localhost:8080/gongzhonghao/CoreServlet"),data);
            System.out.println(post);
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
  }
}
