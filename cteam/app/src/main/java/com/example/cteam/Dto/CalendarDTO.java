package com.example.cteam.Dto;

import java.io.Serializable;

public class CalendarDTO implements Serializable  {

    public String calendar_date;
    public String calendar_icon;
    public String calendar_memo;

    public CalendarDTO(String calendar_date, String calendar_icon, String calendar_memo) {
        this.calendar_date = calendar_date;
        this.calendar_icon = calendar_icon;
        this.calendar_memo = calendar_memo;
    }

    public String getCalendar_date() {
        return calendar_date;
    }
    public void setCalendar_date(String calendar_date) {
        this.calendar_date = calendar_date;
    }
    public String getCalendar_icon() {
        return calendar_icon;
    }
    public void setCalendar_icon(String calendar_icon) {
        this.calendar_icon = calendar_icon;
    }
    public String getCalendar_memo() {
        return calendar_memo;
    }
    public void setCalendar_memo(String calendar_memo) {
        this.calendar_memo = calendar_memo;
    }

}
