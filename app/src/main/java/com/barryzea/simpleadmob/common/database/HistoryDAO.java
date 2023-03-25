package com.barryzea.simpleadmob.common.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.barryzea.simpleadmob.common.entities.HistoryEntity;

import java.util.List;

/****
 * Project SimpleAdMob
 * Created by Barry Zea H. on 25/3/23.
 * Copyright (c)  All rights reserved.
 ***/
@Dao
public interface HistoryDAO {
    @Transaction
    @Insert
    long saveHistory(HistoryEntity historyEntity);
    @Query("select * from HistoryEntity order by timestamp desc")
    List<HistoryEntity> getAllHistory();
    @Query("delete from HistoryEntity where id=:id")
    void deleteMeasure(long id);
    @Query("delete from HistoryEntity")
    void deleteAllHistory();
}

