package com.example.phycology1;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;
@Entity(tableName = "termi")

public class Task  {
    @PrimaryKey
    @NonNull
    @ColumnInfo
    (name = "task")
    private String mTask;

   public Task(@NonNull String task){this.mTask=task;}
@NonNull
    public String getTask() {
        return this.mTask;
    }
}
