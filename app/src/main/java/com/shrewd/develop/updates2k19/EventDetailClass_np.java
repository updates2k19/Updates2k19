package com.shrewd.develop.updates2k19;

public class EventDetailClass_np {

    String eventName,eventDate,eventId,paid;

    public EventDetailClass_np() {
    }

    public EventDetailClass_np(String eventName, String eventDate, String eventId, String paid) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventId = eventId;
        this.paid = paid;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }
}
