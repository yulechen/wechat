package com.mmp.cq.weixin.message.response;

public class RespTestMsg {

    /**
     * 处理换行
     * @return
     */
    public static String getMainMenu() {  
	    StringBuffer buffer = new StringBuffer();  
	    buffer.append("您好，我是小q，请回复数字选择服务：").append("\n\n");  
	    buffer.append("1  天气预报").append("\n");  
	    buffer.append("2  公交查询").append("\n");  
	    buffer.append("3  周边搜索").append("\n");  
	    buffer.append("4  歌曲点播").append("\n");  
	    buffer.append("5  经典游戏").append("\n");  
	    buffer.append("6  美女电台").append("\n");  
	    buffer.append("7  人脸识别").append("\n");  
	    buffer.append("8  聊天唠嗑").append("\n\n");  
	    buffer.append("回复“?”显示此帮助菜单");  
	    return buffer.toString();  
	}
    
    /**
     * 超链接 用双引号
     * @return
     */
    public static String get(){
	return "<a href=\"http://blog.csdn.net/lyq8479\">柳峰的博客</a>" ; 
    }
}
