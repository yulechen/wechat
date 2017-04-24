package com.mmp.cq.weixin.message.request.event;

//上报地理位置事件 用户同意上报地理位置后，每次进入公众号会话时，都会在进入时上报地理位置
//进入会话后每5秒上报一次地理位置，公众号可以在公众平台网站中修改以上设置
public class LocationEvent extends BaseEvent {
    private String Latitude; // 地理位置纬度
    private String Longitude; // 地理位置经度
    private String Precision; // 地理位置精度

    public String getLatitude() {
        return Latitude;
    }


    public void setLatitude(String latitude) {
        Latitude = latitude;
    }


    public String getLongitude() {
        return Longitude;
    }


    public void setLongitude(String longitude) {
        Longitude = longitude;
    }


    public String getPrecision() {
        return Precision;
    }


    public void setPrecision(String precision) {
        Precision = precision;
    }
}
