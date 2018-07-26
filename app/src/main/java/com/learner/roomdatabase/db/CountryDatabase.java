package com.learner.roomdatabase.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import static com.learner.roomdatabase.db.CountryDatabase.DATABASE_VERSION;

@Database(entities = Country.class, version = DATABASE_VERSION)
public abstract class CountryDatabase extends RoomDatabase {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "CountryDatabase";

    public abstract CountryDao countryDao();

    public static CountryDatabase INSTANCE;

    public static CountryDatabase getDatabase(Context context){
        if (INSTANCE == null){
            synchronized (CountryDatabase.class){
                if (INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),CountryDatabase.class,DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
            return INSTANCE;
        }
        return INSTANCE;
    }

}
