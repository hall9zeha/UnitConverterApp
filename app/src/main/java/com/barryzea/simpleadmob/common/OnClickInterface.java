package com.barryzea.simpleadmob.common;

import android.view.View;

import com.barryzea.simpleadmob.common.entities.HistoryEntity;
import com.barryzea.simpleadmob.common.entities.MeasureCardEntity;

/****
 * Project SimpleAdMob
 * Created by Barry Zea H. on 25/3/23.
 * Copyright (c)  All rights reserved.
 ***/
public interface OnClickInterface {
    void onClick(View v,HistoryEntity history);
    void onCardClick(MeasureCardEntity cardEntity);
}
