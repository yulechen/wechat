package com.mmp.cq.weixin.message;

public interface MessageTypes {
     /**
      * 1文本
      * 2图片
      * 3语音
      * 4视频
      * 5小视频
      * 6地理位置
      * 7链接
      * @author Huoyunren
      *
      */
    public static class  RequestTypes{
        /** 
         * 请求消息类型：1文本 
         */  
        public static final String TEXT = "text";  
        /** 
         * 请求消息类型：2图片 
         */  
        public static final String IMAGE = "image";  
        /** 
         * 请求消息类型：3 语音 
         */  
        public static final String VOICE = "voice"; 
        /** 
         * 请求消息类型：4 视频 
         */  
        public static final String VIDEO = "video";  
        /** 
         * 请求消息类型：5 小视频 
         */  
        public static final String SHORTVIDEO = "shortvideo";  
        
        /** 
         * 请求消息类型：6 地理位置 
         */  
        public static final String LOCATION = "location";  
        
        /** 
         * 请求消息类型：7 链接 
         */  
        public static final String LINK = "link";  
        
        /** 
         * 请求消息类型：8 事件
         */  
        public static final String EVENT = "event"; 
      
    }
    
    
    public static class EventTypes{
        public static final String SUBSCRIBE ="subscribe";
        public static final String UNSUBSCRIBE="unsubscribe";
        public static final String SCAN ="SCAN";
        public static final String LOCATION="LOCATION";
        public static final String CLICK="CLICK";
        public static final String VIEW="VIEW";
    }
    /**
     *  1 回复文本消息
        2 回复图片消息
        3 回复语音消息
        4 回复视频消息
        5 回复音乐消息
        6 回复图文消息
     * @author Huoyunren
     *
     */
    public static class ResponseTypes{
        /** 
         * 返回消息类型：
         * 1 文本 
         */  
        public static final String TEXT = "text";  
        /**
         * 2 图片
         */
        public static final String IMAGE = "image"; 
        /**
         * 3、语音
         */
        public static final String VOICE = "voice"; 
         /**
          * 4 视频
          */
        public static final String VIDEO = "video";  
        /** 
         * 返回消息类型：音乐 
         */  
        public static final String MUSIC = "music";  
      
        /** 
         * 返回消息类型：图文 
         */  
        public static final String NEWS = "news";  
    }
    
    
    
    
    
}
