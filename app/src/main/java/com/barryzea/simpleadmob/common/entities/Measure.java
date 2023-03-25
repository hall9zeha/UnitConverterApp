package com.barryzea.simpleadmob.common.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

/****
 * Project SimpleAdMob
 * Created by Barry Zea H. on 25/3/23.
 * Copyright (c)  All rights reserved.
 ***/

public class Measure {
    public Measure(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Measure measure = (Measure) o;
        return id == measure.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Measure(String symbol, String name, int unitSystem, double factor){

        this.symbol= symbol;
        this.name=name;
        this.unitSystem=unitSystem;
        this.factorValue=factor;

    }
    private long id=0;
    private String symbol;
    private String name;
    private int magnitudeId;
    private double factorValue;
    private int unitSystem;
    private double toKg;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {

        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getMagnitudeId() {
        return magnitudeId;
    }

    public void setMagnitudeId(int magnitudeId) {
        this.magnitudeId = magnitudeId;
    }

    public double getFactorValue() {
        return factorValue;
    }

    public void setFactorValue(double factorValue) {
        this.factorValue = factorValue;
    }

    public int getUnitSystem() {
        return unitSystem;
    }

    public void setUnitSystem(int unitSystem) {
        this.unitSystem = unitSystem;
    }

    public double getToKg() {
        return toKg;
    }

    public void setToKg(double toKg) {
        this.toKg = toKg;
    }
}
