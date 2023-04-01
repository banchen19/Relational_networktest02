package com.example.relational_networktest02.ui.home.Home_in_add.event_add.Event_add_Dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Ev_add_dao_entity.class},version = 2,exportSchema = false)
public abstract class Ev_add_dao_base extends RoomDatabase {
    public abstract Ev_add_dao_Dao getEv_add_add_Dao();
}
