package com.example.paul.betprofessor.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface OneResultDao {

    @Insert
    void insert(OneResult result);

    @Update
    void update(OneResult result);

    @Delete
    void delete(OneResult result);

    @Query("DELETE FROM results")
    void deleteAllResults();

    @Query("SELECT * FROM results ORDER BY id DESC LIMIT 20")
    LiveData<List<OneResult>> getLastTwentyResults();

    @Query("SELECT * FROM results WHERE homeTeamName = :name OR guestTeamName = :name")
    List<OneResult> findAllTeamGames(String name);
}
