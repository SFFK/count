package com.cookandroid.countingnumber;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Todo todo);

    @Update
    void update(Todo todo);

    @Delete
    void delete(Todo... todo);

    @Query("SELECT * FROM count_table ORDER BY uid ASC")
    List<Todo> getAll();

    @Query("SELECT count FROM count_table")
    List<String> getCountAll();

    @Query("DELETE FROM count_table")
    void deleteAll();
}

