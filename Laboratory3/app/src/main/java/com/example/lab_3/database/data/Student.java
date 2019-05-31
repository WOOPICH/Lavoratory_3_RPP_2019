package com.example.lab_3.database.data;

import nl.qbusict.cupboard.annotation.Column;

public class Student {
    private Long _id;
    private String fio;
    @Column("date_joined")
    private long dateJoined;

    public Student() {
    }

    public Student(String fio) {
        this.fio = fio;
        dateJoined = System.currentTimeMillis();
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public long getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(long dateJoined) {
        this.dateJoined = dateJoined;
    }
}
