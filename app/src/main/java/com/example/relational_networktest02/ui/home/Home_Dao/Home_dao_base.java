package com.example.relational_networktest02.ui.home.Home_Dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Home_dao_entity.class},version = 2,exportSchema = false)
public abstract class Home_dao_base extends RoomDatabase {
    public abstract Home_dao_Dao getHome_dao_Dao();
}
