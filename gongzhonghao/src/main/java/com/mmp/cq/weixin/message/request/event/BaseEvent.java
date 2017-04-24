package com.mmp.cq.weixin.message.request.event;

import com.mmp.cq.weixin.message.request.BaseMessage;

public class BaseEvent extends BaseMessage {
    private String EventKey;
    private String Ticket;
    private String Event;
    public String getEventKey() {
        return EventKey;
    }
    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }
    public String getTicket() {
        return Ticket;
    }
    public void setTicket(String ticket) {
        Ticket = ticket;
    }
    public String getEvent() {
        return Event;
    }
    public void setEvent(String event) {
        Event = event;
    }

    
}
