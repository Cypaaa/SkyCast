package com.example.skycast.Packages.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Situation.class}, version = 1)
public abstract class SituationDatabase extends RoomDatabase {
    public abstract SituationDAO getSituationDAO();
}
