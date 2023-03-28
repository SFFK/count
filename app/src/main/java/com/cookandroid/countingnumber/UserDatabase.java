package com.cookandroid.countingnumber;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Todo.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDao getUserDao();

    private static UserDatabase INSTANCE;

    public static UserDatabase getDBInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, UserDatabase.class, "todo")
                    .allowMainThreadQueries()
                    .build();

        }

        return INSTANCE;
    }


}
