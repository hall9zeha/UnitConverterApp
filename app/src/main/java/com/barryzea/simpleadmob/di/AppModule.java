package com.barryzea.simpleadmob.di;

import android.app.Application;

import androidx.room.Room;

import com.barryzea.simpleadmob.common.database.HistoryDAO;
import com.barryzea.simpleadmob.common.database.HistoryDatabase;
import com.barryzea.simpleadmob.common.entities.HistoryEntity;
import com.barryzea.simpleadmob.model.Repository;
import com.barryzea.simpleadmob.model.RepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

/****
 * Project SimpleAdMob
 * Created by Barry Zea H. on 25/3/23.
 * Copyright (c)  All rights reserved.
 ***/
@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Singleton
    @Provides
    public HistoryDAO dataBaseProvider(Application app){
        return Room.databaseBuilder(app, HistoryDatabase.class,"history_db").fallbackToDestructiveMigration().build().historyDao();
    }

    @Provides
    public Repository repositoryProvider(HistoryDAO db){
        return new RepositoryImpl(db);
    }
}
