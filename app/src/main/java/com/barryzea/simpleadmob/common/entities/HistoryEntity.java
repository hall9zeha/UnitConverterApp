package com.barryzea.simpleadmob.common.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

/****
 * Project SimpleAdMob
 * Created by Barry Zea H. on 25/3/23.
 * Copyright (c)  All rights reserved.
 ***/
@Entity
public class HistoryEntity {
    @PrimaryKey(autoGenerate = true) private long id;
    private String fromUnitName;
    private String toUnitName;
    private double fromUnitValue;
    private double toUnitValue;
    private long timestamp;
    private double scientificNotation;
    public HistoryEntity() {

    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoryEntity that = (HistoryEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getFromUnitName() {
        return fromUnitName;
    }

    public void setFromUnitName(String fromUnitName) {
        this.fromUnitName = fromUnitName;
    }

    public String getToUnitName() {
        return toUnitName;
    }

    public void setToUnitName(String toUnitName) {
        this.toUnitName = toUnitName;
    }

    public double getFromUnitValue() {
        return fromUnitValue;
    }

    public void setFromUnitValue(double fromUnitValue) {
        this.fromUnitValue = fromUnitValue;
    }

    public double getToUnitValue() {
        return toUnitValue;
    }

    public void setToUnitValue(double toUnitValue) {
        this.toUnitValue = toUnitValue;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getScientificNotation() {
        return scientificNotation;
    }

    public void setScientificNotation(double scientificNotation) {
        this.scientificNotation = scientificNotation;
    }
}
