package com.synnex.message.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.Consts;
import org.apache.http.util.CharArrayBuffer;
import org.apache.log4j.Logger;

import com.mmp.cq.utils.XmlUtils;
import com.mmp.cq.weixin.message.MessageTypes.EventTypes;
import com.mmp.cq.weixin.message.MessageTypes.RequestTypes;
import com.mmp.cq.weixin.message.request.BaseMessage;
import com.mmp.cq.weixin.message.request.ImageMessage;
import com.mmp.cq.weixin.message.request.LinkMessage;
import com.mmp.cq.weixin.message.request.LocationMessage;
import com.mmp.cq.weixin.message.request.ShortVideoMessage;
import com.mmp.cq.weixin.message.request.TextMessage;
import com.mmp.cq.weixin.message.request.VideoMessage;
import com.mmp.cq.weixin.message.request.VoiceMessage;
import com.mmp.cq.weixin.message.request.event.BaseEvent;
import com.mmp.cq.weixin.message.request.event.LocationEvent;
import com.mmp.cq.weixin.utils.ResponseUtils;


public class CoreService {  
    
    private static final Logger log =Logger.getLogger(CoreService.class);
    /** 
     * 处理微信发来的请求 
     *  
     * @param request 
     * @return 
     */  
    public static String processRequest(HttpServletRequest request) {  
        BaseMessage baseMessage =null;
        try {
            String  requestXml= getRequestXMLContent(request);
            log.info("收到微信服务器发了的消息："+requestXml);
            baseMessage = XmlUtils.toObject(requestXml, BaseMessage.class);
            String debugMessage = "";
            //1
            if(RequestTypes.TEXT.equals(baseMessage.getMsgType())){
                TextMessage textMessage = XmlUtils.toObject(requestXml,TextMessage.class);
                debugMessage="您发送的是文本消息";
                if("weijsdemo".equals(textMessage.getContent())){
                    return  ResponseUtils.responsejsDemoNewsMessage(baseMessage.getFromUserName(), baseMessage.getToUserName());
                }
            // 2    
            }else if(RequestTypes.IMAGE.equals(baseMessage.getMsgType())){
                ImageMessage imageMessage = XmlUtils.toObject(requestXml, ImageMessage.class);
                debugMessage="您发送的是图片消息";
            // 3 
            }else if (RequestTypes.LINK.equals(baseMessage.getMsgType())){
                LinkMessage linkMessage = XmlUtils.toObject(requestXml, LinkMessage.class);
                debugMessage="您发送的是连接消息";
            // 4
            }else if (RequestTypes.LOCATION.equals(baseMessage.getMsgType())){
                LocationMessage message = XmlUtils.toObject(requestXml, LocationMessage.class);
                debugMessage="您发送的是位置消息";
            // 5    
            }else if (RequestTypes.SHORTVIDEO.equals(baseMessage.getMsgType())){
                ShortVideoMessage message = XmlUtils.toObject(requestXml, ShortVideoMessage.class);
                debugMessage="您发送的是小视屏消息";
            // 6    
            }else if (RequestTypes.VIDEO.equals(baseMessage.getMsgType())){
                VideoMessage message = XmlUtils.toObject(requestXml, VideoMessage.class);
                debugMessage="您发送的是视屏消息";
             // 7    
            }else if (RequestTypes.VOICE.equals(baseMessage.getMsgType())){
                VoiceMessage message = XmlUtils.toObject(requestXml,VoiceMessage.class);
                debugMessage="您发送的是语音消息";
             // 8 
            }else if (RequestTypes.EVENT.equals(baseMessage.getMsgType())){
                BaseEvent event = XmlUtils.toObject(requestXml, BaseEvent.class);
                if(EventTypes.SUBSCRIBE.equals(event.getEvent())){
                    debugMessage="感谢您订阅本公众号事件";
                 }else if(EventTypes.UNSUBSCRIBE.equals(event.getEvent())){
                     debugMessage="取消订阅事件";
                 }else if(EventTypes.SCAN.equals(event.getEvent())){
                     debugMessage="二维码扫描事件";
                 }else if(EventTypes.LOCATION.equals(event.getEvent())){
                     LocationEvent location = XmlUtils.toObject(requestXml, LocationEvent.class);
                     debugMessage="位置上报事件";
                     
                 }else if(EventTypes.CLICK.equals(event.getEvent())){
                     debugMessage="点击菜单事件";
                 }else if(EventTypes.VIEW.equals(event.getEvent())){
                     debugMessage="点击菜单访问连接事件";
                 }
            }
            
            log.info("responsemes"+debugMessage);
            return ResponseUtils.responseText(baseMessage, debugMessage);
        }
        catch (IOException e) {
            e.printStackTrace();
            log.error("",e);
        }
        return ResponseUtils.responseDefaultText(baseMessage);  
    }  
    
    private static String getRequestXMLContent(HttpServletRequest request) throws IOException{
        final InputStream instream = request.getInputStream();
        if (instream == null) {
            return null;
        }
        try {
            int i = instream.available();
            if (i < 0) i = 4096;
            Charset charset =Consts.UTF_8;
            final Reader reader = new InputStreamReader(instream, charset);
            final CharArrayBuffer buffer = new CharArrayBuffer(i);
            final char[] tmp = new char[1024];
            int l;
            while((l = reader.read(tmp)) != -1) {
                buffer.append(tmp, 0, l);
            }
            return buffer.toString();
        } finally {
            instream.close();
        }
    }
    
}  