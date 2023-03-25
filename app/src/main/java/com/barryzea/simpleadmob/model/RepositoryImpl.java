package com.barryzea.simpleadmob.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.barryzea.simpleadmob.common.SingleMutableLiveData;
import com.barryzea.simpleadmob.common.database.HistoryDAO;
import com.barryzea.simpleadmob.common.database.HistoryDatabase;
import com.barryzea.simpleadmob.common.entities.HistoryEntity;

import java.util.List;
import java.util.concurrent.Executors;

import javax.inject.Inject;

/****
 * Project SimpleAdMob
 * Created by Barry Zea H. on 25/3/23.
 * Copyright (c)  All rights reserved.
 ***/
public class RepositoryImpl implements Repository {
    private HistoryDAO db;
    private SingleMutableLiveData<List<HistoryEntity>> historyList= new SingleMutableLiveData<>();
    @Inject
    public RepositoryImpl(HistoryDAO db){
        this.db= db;
    }
    @Override
    public void saveHistory(HistoryEntity history) {
        Executors.newSingleThreadExecutor().execute(()->db.saveHistory(history));
    }

    @Override
    public void callHistory() {
       Executors.newSingleThreadExecutor().execute(()->{
                   historyList.postValue(db.getAllHistory());
       });
    }

    @Override
    public SingleMutableLiveData<List<HistoryEntity>> getAllHistory() {
       return historyList;
    }
    @Override
    public void deleteAllHistory() {
        Executors.newSingleThreadExecutor().execute(()->db.deleteAllHistory());
    }

    @Override
    public void deleteOldHistoryItem(HistoryEntity history) {
        Executors.newSingleThreadExecutor().execute(()->db.deleteMeasure(history.getId()));
    }
}
