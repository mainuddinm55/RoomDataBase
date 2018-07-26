package com.learner.roomdatabase.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface CountryDao {

    @Query("SELECT * FROM Country ORDER BY id")
    LiveData<List<Country>> getAllCountry();

    @Insert
    void insert(Country country);

    @Query("DELETE FROM country")
    void deleteAllCountry();

    @Query("DELETE FROM country WHERE id=:id")
    void deleteById(int id);
}
