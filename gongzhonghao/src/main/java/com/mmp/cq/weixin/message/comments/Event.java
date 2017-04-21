package com.mmp.cq.weixin.message.comments;

/**
 * 用户事件消息
 * @author Huoyunren
 *
 */
public class Event {

    /**
   1、 关注/取消关注事件
    
<xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[FromUser]]></FromUserName>
<CreateTime>123456789</CreateTime>
<MsgType><![CDATA[event]]></MsgType>
<Event><![CDATA[subscribe]]></Event>
</xml>
     * 
     参数 描述
ToUserName  开发者微信号
FromUserName    发送方帐号（一个OpenID）
CreateTime  消息创建时间 （整型）
MsgType 消息类型，event
Event   事件类型，subscribe(订阅)、unsubscribe(取消订阅)
     *
     *
     2、扫描带参数二维码事件
     2.1用户未关注时，进行关注后的事件推送
     
     <xml><ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[FromUser]]></FromUserName>
<CreateTime>123456789</CreateTime>
<MsgType><![CDATA[event]]></MsgType>
<Event><![CDATA[subscribe]]></Event>
<EventKey><![CDATA[qrscene_123123]]></EventKey>
<Ticket><![CDATA[TICKET]]></Ticket>
</xml>
     
     ToUserName 开发者微信号
FromUserName    发送方帐号（一个OpenID）
CreateTime  消息创建时间 （整型）
MsgType 消息类型，event
Event   事件类型，subscribe
EventKey    事件KEY值，qrscene_为前缀，后面为二维码的参数值
Ticket  二维码的ticket，可用来换取二维码图片

    2.2 用户已关注时的事件推送
    
    <xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[FromUser]]></FromUserName>
<CreateTime>123456789</CreateTime>
<MsgType><![CDATA[event]]></MsgType>
<Event><![CDATA[SCAN]]></Event>
<EventKey><![CDATA[SCENE_VALUE]]></EventKey>
<Ticket><![CDATA[TICKET]]></Ticket>

ToUserName  开发者微信号
FromUserName    发送方帐号（一个OpenID）
CreateTime  消息创建时间 （整型）
MsgType 消息类型，event
Event   事件类型，SCAN
EventKey    事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
Ticket  二维码的ticket，可用来换取二维码图片
</xml>
     * 
     * 
     * 
     3、上报地理位置事件 用户同意上报地理位置后，每次进入公众号会话时，都会在进入时上报地理位置，
         或在进入会话后每5秒上报一次地理位置，公众号可以在公众平台网站中修改以上设置
     
     <xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[fromUser]]></FromUserName>
<CreateTime>123456789</CreateTime>
<MsgType><![CDATA[event]]></MsgType>
<Event><![CDATA[LOCATION]]></Event>
<Latitude>23.137466</Latitude>
<Longitude>113.352425</Longitude>
<Precision>119.385040</Precision>
</xml>

ToUserName  开发者微信号
FromUserName    发送方帐号（一个OpenID）
CreateTime  消息创建时间 （整型）
MsgType 消息类型，event
Event   事件类型，LOCATION
Latitude    地理位置纬度
Longitude   地理位置经度
Precision   地理位置精度

 4、自定义菜单事件
 
 4.1点击菜单拉取消息时的事件推送
 <xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[FromUser]]></FromUserName>
<CreateTime>123456789</CreateTime>
<MsgType><![CDATA[event]]></MsgType>
<Event><![CDATA[CLICK]]></Event>
<EventKey><![CDATA[EVENTKEY]]></EventKey>
</xml>

ToUserName  开发者微信号
FromUserName    发送方帐号（一个OpenID）
CreateTime  消息创建时间 （整型）
MsgType 消息类型，event
Event   事件类型，CLICK
EventKey    事件KEY值，与自定义菜单接口中KEY值对应

4.2点击菜单跳转链接时的事件推送
<xml>
<ToUserName><![CDATA[toUser]]></ToUserName>
<FromUserName><![CDATA[FromUser]]></FromUserName>
<CreateTime>123456789</CreateTime>
<MsgType><![CDATA[event]]></MsgType>
<Event><![CDATA[VIEW]]></Event>
<EventKey><![CDATA[www.qq.com]]></EventKey>
</xml>

ToUserName  开发者微信号
FromUserName    发送方帐号（一个OpenID）
CreateTime  消息创建时间 （整型）
MsgType 消息类型，event
Event   事件类型，VIEW
EventKey    事件KEY值，设置的跳转URL
 
*/
    
}
