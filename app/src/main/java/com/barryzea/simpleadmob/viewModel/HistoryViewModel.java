package com.barryzea.simpleadmob.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.barryzea.simpleadmob.common.SingleMutableLiveData;
import com.barryzea.simpleadmob.common.entities.HistoryEntity;
import com.barryzea.simpleadmob.model.Repository;
import com.barryzea.simpleadmob.model.RepositoryImpl;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

/****
 * Project SimpleAdMob
 * Created by Barry Zea H. on 25/3/23.
 * Copyright (c)  All rights reserved.
 ***/
@HiltViewModel
public class HistoryViewModel extends ViewModel {

    public SingleMutableLiveData<List<HistoryEntity>> getAllHistory() {
        return repository.getAllHistory();
    }
    RepositoryImpl repository;
    @Inject
    public HistoryViewModel (RepositoryImpl repository){
        this.repository= repository;
    }
    public void saveHistory(HistoryEntity history){
        repository.saveHistory(history);
    }
    public void deleteAllHistory(){
        repository.deleteAllHistory();
    }
    public void callHistory(){
        repository.callHistory();
    }
    public void deleteOldHistoryItem(HistoryEntity entity){
        repository.deleteOldHistoryItem(entity);
    }

}
