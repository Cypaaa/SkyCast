package com.example.skycast.Packages.Room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SituationDAO {
    @Insert
    public void addSituation(Situation situation);
    @Query("select * from situation where id = :id")
    public Situation getSituation(int id);
    @Update
    public void updateSituation(Situation situation);
    @Delete
    public void deleteSituation(Situation situation);
    @Query("select * from situation")
    public List<Situation> getAllSituation();
}
