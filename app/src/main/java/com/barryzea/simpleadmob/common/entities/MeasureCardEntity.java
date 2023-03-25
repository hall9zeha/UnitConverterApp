package com.barryzea.simpleadmob.common.entities;

/****
 * Project SimpleAdMob
 * Created by Barry Zea H. on 25/3/23.
 * Copyright (c)  All rights reserved.
 ***/
public class MeasureCardEntity {
    private String nameCard;
    private int unitType;
    private int imageRes;

    public MeasureCardEntity(String nameCard, int unitType, int imageRes) {
        this.nameCard = nameCard;
        this.unitType = unitType;
        this.imageRes = imageRes;
    }

    public String getNameCard() {
        return nameCard;
    }

    public void setNameCard(String nameCard) {
        this.nameCard = nameCard;
    }

    public int getUnitType() {
        return unitType;
    }

    public void setUnitType(int unitType) {
        this.unitType = unitType;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }
}
