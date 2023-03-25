package com.barryzea.simpleadmob.model;

import androidx.lifecycle.LiveData;

import com.barryzea.simpleadmob.common.entities.HistoryEntity;

import java.util.List;

/****
 * Project SimpleAdMob
 * Created by Barry Zea H. on 25/3/23.
 * Copyright (c)  All rights reserved.
 ***/
public interface Repository {
    void saveHistory(HistoryEntity history);
    void callHistory();
    LiveData<List<HistoryEntity>> getAllHistory();
    void deleteAllHistory();
    void deleteOldHistoryItem(HistoryEntity history);
}
