package com.hacktiv8.todolist.data.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.hacktiv8.todolist.data.model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Note note);

    @Update()
    void update(Note note);

    @Query("SELECT * from note ORDER BY isDone ASC")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * from note WHERE id = :id")
    LiveData<Note> getNoteById(int id);

    @Delete
    void delete(Note note);
}
