package com.example.relational_networktest02.ui.home.Home_Dao;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Home_dao_entity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name="Head_address")
    public String HEAD_ADDRESS;
    @ColumnInfo(name="iadd_name")
    public String IADD_NAME ;
    @ColumnInfo(name = "iadd_gender")
    public String IADD_GENDER;
    @ColumnInfo(name = "iadd_age")
    public String IADD_AGE;
    @ColumnInfo(name = "iadd_nationality")
    public String IADD_NATIONALITY;
    @ColumnInfo(name = "iadd_height")
    public String IADD_HEIGHT;
    @ColumnInfo(name = "iadd_weight")
    public String IADD_WEIGHT;
    @ColumnInfo(name = "iadd_identity")
    public String IADD_IDENTITY;
    @ColumnInfo(name = "iadd_number")
    public String IADD_NUMBER;

    public Home_dao_entity( String HEAD_ADDRESS, String IADD_NAME, String IADD_GENDER, String IADD_AGE, String IADD_NATIONALITY, String IADD_HEIGHT, String IADD_WEIGHT, String IADD_IDENTITY, String IADD_NUMBER) {
        this.HEAD_ADDRESS = HEAD_ADDRESS;
        this.IADD_NAME = IADD_NAME;
        this.IADD_GENDER = IADD_GENDER;
        this.IADD_AGE = IADD_AGE;
        this.IADD_NATIONALITY = IADD_NATIONALITY;
        this.IADD_HEIGHT = IADD_HEIGHT;
        this.IADD_WEIGHT = IADD_WEIGHT;
        this.IADD_IDENTITY = IADD_IDENTITY;
        this.IADD_NUMBER = IADD_NUMBER;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHEAD_ADDRESS() {
        return HEAD_ADDRESS;
    }

    public void setHEAD_ADDRESS(String HEAD_ADDRESS) {
        this.HEAD_ADDRESS = HEAD_ADDRESS;
    }

    public String getIADD_NAME() {
        return IADD_NAME;
    }

    public void setIADD_NAME(String IADD_NAME) {
        this.IADD_NAME = IADD_NAME;
    }

    public String getIADD_GENDER() {
        return IADD_GENDER;
    }

    public void setIADD_GENDER(String IADD_GENDER) {
        this.IADD_GENDER = IADD_GENDER;
    }

    public String getIADD_AGE() {
        return IADD_AGE;
    }

    public void setIADD_AGE(String IADD_AGE) {
        this.IADD_AGE = IADD_AGE;
    }

    public String getIADD_NATIONALITY() {
        return IADD_NATIONALITY;
    }

    public void setIADD_NATIONALITY(String IADD_NATIONALITY) {
        this.IADD_NATIONALITY = IADD_NATIONALITY;
    }

    public String getIADD_HEIGHT() {
        return IADD_HEIGHT;
    }

    public void setIADD_HEIGHT(String IADD_HEIGHT) {
        this.IADD_HEIGHT = IADD_HEIGHT;
    }

    public String getIADD_WEIGHT() {
        return IADD_WEIGHT;
    }

    public void setIADD_WEIGHT(String IADD_WEIGHT) {
        this.IADD_WEIGHT = IADD_WEIGHT;
    }

    public String getIADD_IDENTITY() {
        return IADD_IDENTITY;
    }

    public void setIADD_IDENTITY(String IADD_IDENTITY) {
        this.IADD_IDENTITY = IADD_IDENTITY;
    }

    public String getIADD_NUMBER() {
        return IADD_NUMBER;
    }

    public void setIADD_NUMBER(String IADD_NUMBER) {
        this.IADD_NUMBER = IADD_NUMBER;
    }
}
