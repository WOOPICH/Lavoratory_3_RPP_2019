package com.example.lab_3.database.data;

import nl.qbusict.cupboard.annotation.Column;

public class Student {
    private Long _id;
    private String firstName;
    private String secondName;
    private String middleName;
    @Column("date_joined")
    private long dateJoined;

    public Student() {
    }

    public Student(String fio) {
        String[] fioSplited = fio.split(" ");
        firstName = fioSplited[0];
        secondName = fioSplited[1];
        middleName = fioSplited[2];
        dateJoined = System.currentTimeMillis();
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public long getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(long dateJoined) {
        this.dateJoined = dateJoined;
    }
}
