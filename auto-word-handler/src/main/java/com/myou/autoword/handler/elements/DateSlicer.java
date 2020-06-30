package com.myou.autoword.handler.elements;

import com.myou.autoword.handler.exceptions.TemplateValidationException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @Author myou
 * @Date 2020/6/30  10:59 上午
 */
public class DateSlicer {
    private Date date;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    private int year;
    private int month;
    private int day;

    public DateSlicer(){

    }
    public DateSlicer(Date date) {
        this();
        this.date = date;
        if (null==this.date){
            throw new TemplateValidationException("Type DateSlicer attribute date can not be null");
        }
        LocalDate localDate = this.date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.setYear(localDate.getYear());
        this.setMonth(localDate.getMonth().getValue());
        this.setDay(localDate.getDayOfMonth());
    }
}
