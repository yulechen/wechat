package com.synnex.message.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.mmp.cq.utils.XmlUtils;
import com.mmp.cq.weixin.message.MessageTypes.RequestTypes;
import com.mmp.cq.weixin.message.MessageUtil;
import com.mmp.cq.weixin.message.request.BaseMessage;
import com.mmp.cq.weixin.message.request.ImageMessage;
import com.mmp.cq.weixin.message.request.LinkMessage;
import com.mmp.cq.weixin.message.request.LocationMessage;
import com.mmp.cq.weixin.message.request.ShortVideoMessage;
import com.mmp.cq.weixin.message.request.TextMessage;
import com.mmp.cq.weixin.message.request.VideoMessage;
import com.mmp.cq.weixin.message.request.VoiceMessage;
import com.mmp.cq.weixin.message.response.Article;
import com.mmp.cq.weixin.message.response.NewsMessage;
import com.mmp.cq.weixin.message.response.RespTestMsg;
import com.mmp.cq.weixin.message.response.ResponseUtils;


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
            String  requestXml= MessageUtil.getRequestXMLContent(request);
            log.info("收到微信服务器发了的消息："+requestXml);
            baseMessage = XmlUtils.toObject(requestXml, BaseMessage.class);
            //1
            if(RequestTypes.TEXT.equals(baseMessage.getMsgType())){
                TextMessage textMessage = XmlUtils.toObject(requestXml,TextMessage.class);
            // 2    
            }else if(RequestTypes.IMAGE.equals(baseMessage.getMsgType())){
                ImageMessage imageMessage = XmlUtils.toObject(requestXml, ImageMessage.class);
            // 3 
            }else if (RequestTypes.LINK.equals(baseMessage.getMsgType())){
                LinkMessage linkMessage = XmlUtils.toObject(requestXml, LinkMessage.class);
            // 4
            }else if (RequestTypes.LOCATION.equals(baseMessage.getMsgType())){
                LocationMessage message = XmlUtils.toObject(requestXml, LocationMessage.class);
            // 5    
            }else if (RequestTypes.SHORTVIDEO.equals(baseMessage.getMsgType())){
                ShortVideoMessage message = XmlUtils.toObject(requestXml, ShortVideoMessage.class);
            // 6    
            }else if (RequestTypes.VIDEO.equals(baseMessage.getMsgType())){
                VideoMessage message = XmlUtils.toObject(requestXml, VideoMessage.class);
             // 7    
            }else if (RequestTypes.VOICE.equals(baseMessage.getMsgType())){
                VoiceMessage message = XmlUtils.toObject(requestXml,VoiceMessage.class);
             // 8 
            }else if (RequestTypes.EVENT.equals(baseMessage.getMsgType())){
                
            }
            
            log.info("responsemes"+ResponseUtils.responseDefaultText(baseMessage));
            
        }
        catch (IOException e) {
            e.printStackTrace();
            log.error("",e);
        }
        return ResponseUtils.responseDefaultText(baseMessage);  
    }  
}  