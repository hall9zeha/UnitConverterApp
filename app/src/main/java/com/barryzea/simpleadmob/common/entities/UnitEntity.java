package com.barryzea.simpleadmob.common.entities;

/****
 * Project SimpleAdMob
 * Created by Barry Zea H. on 25/3/23.
 * Copyright (c)  All rights reserved.
 ***/
public class UnitEntity {
    private int firstUnitSystem;
    private int secondUnitSystem;
    private double firstUnitFactorValue;
    private double secondUnitFactorValue;
    private String firstUnitName;
    private String secondUnitName;
    private String firstUnitSymbol;
    private String secondUnitSymbol;
    private int firstIndex;
    private int secondIndex;

    public UnitEntity(){}
    public int getFirstUnitSystem() {
        return firstUnitSystem;
    }

    public void setFirstUnitSystem(int firstUnitSystem) {
        this.firstUnitSystem = firstUnitSystem;
    }

    public int getSecondUnitSystem() {
        return secondUnitSystem;
    }

    public void setSecondUnitSystem(int secondUnitSystem) {
        this.secondUnitSystem = secondUnitSystem;
    }

    public double getFirstUnitFactorValue() {
        return firstUnitFactorValue;
    }

    public void setFirstUnitFactorValue(double firstUnitFactorValue) {
        this.firstUnitFactorValue = firstUnitFactorValue;
    }

    public double getSecondUnitFactorValue() {
        return secondUnitFactorValue;
    }

    public void setSecondUnitFactorValue(double secondUnitFactorValue) {
        this.secondUnitFactorValue = secondUnitFactorValue;
    }

    public String getFirstUnitName() {
        return firstUnitName;
    }

    public void setFirstUnitName(String firstUnitName) {
        this.firstUnitName = firstUnitName;
    }

    public String getSecondUnitName() {
        return secondUnitName;
    }

    public void setSecondUnitName(String secondUnitName) {
        this.secondUnitName = secondUnitName;
    }

    public String getFirstUnitSymbol() {
        return firstUnitSymbol;
    }

    public void setFirstUnitSymbol(String firstUnitSymbol) {
        this.firstUnitSymbol = firstUnitSymbol;
    }

    public String getSecondUnitSymbol() {
        return secondUnitSymbol;
    }

    public void setSecondUnitSymbol(String secondUnitSymbol) {
        this.secondUnitSymbol = secondUnitSymbol;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public void setFirstIndex(int firstIndex) {
        this.firstIndex = firstIndex;
    }

    public int getSecondIndex() {
        return secondIndex;
    }

    public void setSecondIndex(int secondIndex) {
        this.secondIndex = secondIndex;
    }


}
