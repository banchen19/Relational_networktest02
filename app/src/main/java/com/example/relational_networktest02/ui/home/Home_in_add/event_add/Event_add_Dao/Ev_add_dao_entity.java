package com.example.relational_networktest02.ui.home.Home_in_add.event_add.Event_add_Dao;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Ev_add_dao_entity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "ev_add_name")
    public String ev_add_name;
    @ColumnInfo(name = "ev_add_text")
    public String ev_add_text;
    @ColumnInfo(name = "ev_add_text_key_words")
    public String ev_add_text_key_words;

    public Ev_add_dao_entity(String ev_add_name, String ev_add_text, String ev_add_text_key_words) {
        this.ev_add_name = ev_add_name;
        this.ev_add_text = ev_add_text;
        this.ev_add_text_key_words = ev_add_text_key_words;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEv_add_name() {
        return ev_add_name;
    }

    public void setEv_add_name(String ev_add_name) {
        this.ev_add_name = ev_add_name;
    }

    public String getEv_add_text() {
        return ev_add_text;
    }

    public void setEv_add_text(String ev_add_text) {
        this.ev_add_text = ev_add_text;
    }

    public String getEv_add_text_key_words() {
        return ev_add_text_key_words;
    }

    public void setEv_add_text_key_words(String ev_add_text_key_words) {
        this.ev_add_text_key_words = ev_add_text_key_words;
    }
}