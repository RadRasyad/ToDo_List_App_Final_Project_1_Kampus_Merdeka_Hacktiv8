package com.hacktiv8.todolist.data.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.hacktiv8.todolist.data.model.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();

    private static volatile NoteDatabase INSTANCE;

    public static NoteDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (NoteDatabase.class) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                NoteDatabase.class, "note_database")
                        .build();
            }
        }
        return INSTANCE;
    }
}
