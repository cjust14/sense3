package com.example.cecil.database2;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by cecil on 16-04-2018.
 */
@Dao
public interface MyDao
{
    @Insert
    public void addMad(Mad mad);

    @Query("select * from madkasse")
    public List<Mad> getMad();

    @Update
    public void updateMad(Mad mad);

}
