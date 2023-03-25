package com.barryzea.simpleadmob.common.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.barryzea.simpleadmob.common.entities.HistoryEntity;

/****
 * Project SimpleAdMob
 * Created by Barry Zea H. on 25/3/23.
 * Copyright (c)  All rights reserved.
 ***/
@Database(entities = {HistoryEntity.class},version = 1)
public abstract class HistoryDatabase extends RoomDatabase {
    public abstract HistoryDAO historyDao();
}
