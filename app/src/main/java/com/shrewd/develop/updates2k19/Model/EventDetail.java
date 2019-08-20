package com.shrewd.develop.updates2k19.Model;

public class EventDetail {

    String description = "description";
    String event_type = "event_type";
    String faculty_coordinator = "faculty_coordinator";
    String flyer = "flyer";
    String icon_url = "icon_url";
    String ln_hindi = "ln_hindi";
    String name = "name";
    String poster = "poster";
    String qualified_participants = "qualified_participants";
    String schedule = "schedule";
    String student_coordinator = "student_coordinator";
    String student_volunteer = "student_volunteer";
    String tagline = "tagline";
    String end_time = "end_time";
    String location_desc = "location_desc";
    String start_time = "start_time";

    public EventDetail(String description, String event_type, String faculty_coordinator, String flyer, String icon_url, String ln_hindi, String name, String poster, String qualified_participants, String schedule, String student_coordinator, String student_volunteer, String tagline, String end_time, String location_desc, String start_time) {
        this.description = description;
        this.event_type = event_type;
        this.faculty_coordinator = faculty_coordinator;
        this.flyer = flyer;
        this.icon_url = icon_url;
        this.ln_hindi = ln_hindi;
        this.name = name;
        this.poster = poster;
        this.qualified_participants = qualified_participants;
        this.schedule = schedule;
        this.student_coordinator = student_coordinator;
        this.student_volunteer = student_volunteer;
        this.tagline = tagline;
        this.end_time = end_time;
        this.location_desc = location_desc;
        this.start_time = start_time;
    }

    public String getDescription() {
        return description;
    }

    public String getEvent_type() {
        return event_type;
    }

    public String getFaculty_coordinator() {
        return faculty_coordinator;
    }

    public String getFlyer() {
        return flyer;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public String getLn_hindi() {
        return ln_hindi;
    }

    public String getName() {
        return name;
    }

    public String getPoster() {
        return poster;
    }

    public String getQualified_participants() {
        return qualified_participants;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getStudent_coordinator() {
        return student_coordinator;
    }

    public String getStudent_volunteer() {
        return student_volunteer;
    }

    public String getTagline() {
        return tagline;
    }

    public String getEnd_time() {
        return end_time;
    }

    public String getLocation_desc() {
        return location_desc;
    }

    public String getStart_time() {
        return start_time;
    }
}
