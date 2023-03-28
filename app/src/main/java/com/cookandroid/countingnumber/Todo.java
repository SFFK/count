package com.cookandroid.countingnumber;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "count_table")
public class Todo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "count")
    private String count;

    @ColumnInfo(name = "result")
    private int result;

    public Todo(String count, int result) {
        this.count = count;
        this.result = result;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
