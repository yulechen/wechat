package com.mmp.cq.weixin.message.request;

public class TextMessage extends BaseMessage {  
    // 消息内容  
    private String Content;  
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }

    
    @Override
    public String toString() {
        return "TextMessage [Content=" + Content + ", getToUserName()=" + getToUserName()
                + ", getFromUserName()=" + getFromUserName() + ", getCreateTime()=" + getCreateTime()
                + ", getMsgType()=" + getMsgType() + ", getMsgId()=" + getMsgId() + "]";
    }  
}  