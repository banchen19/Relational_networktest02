package com.example.relational_networktest02.ui.home.Home_in_add.event_add.Event_add_Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface Ev_add_dao_Dao {
    @Insert
    void insertWords(Ev_add_dao_entity...ev_add_dao_entities);
    @Delete
        //根据条件删除数据
    void deleteWords(Ev_add_dao_entity...ev_add_dao_entities);
    //删除所有数据
    @Query("DELETE FROM Ev_add_dao_entity")
    void deleteAllWords();
    //更新数据
    @Update
    void updateWords(Ev_add_dao_entity...ev_add_dao_entities);
    //查询返回所有数据
    @Query("SELECT * FROM Ev_add_dao_entity ORDER BY ID DESC")
    List<Ev_add_dao_entity> getAll_Ev_Item();
}
