package com.example.relational_networktest02.ui.home.Home_Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Home_dao_Dao {
    //添加数据
    @Insert
    void insertWords(Home_dao_entity...home_dao_entities);
    @Delete
        //根据条件删除数据
    void deleteWords(Home_dao_entity...home_dao_entities);
    //删除所有数据
    @Query("DELETE FROM Home_dao_entity")
    void deleteAllWords();
    //更新数据
    @Update
    void updateWords(Home_dao_entity...home_dao_entities);
    //查询返回所有数据
    @Query("SELECT * FROM Home_dao_entity ORDER BY ID DESC")
    List<Home_dao_entity> getAllHome_Item();
}
