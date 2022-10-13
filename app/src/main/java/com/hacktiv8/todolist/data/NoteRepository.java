package com.hacktiv8.todolist.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.hacktiv8.todolist.data.model.Note;
import com.hacktiv8.todolist.data.room.NoteDao;
import com.hacktiv8.todolist.data.room.NoteDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {
    private final NoteDao mNotesDao;
    private final ExecutorService executorService;

    public NoteRepository(Application application) {
        executorService = Executors.newSingleThreadExecutor();
        NoteDatabase db = NoteDatabase.getDatabase(application);
        mNotesDao = db.noteDao();
    }

    public LiveData<List<Note>> getAllNotes() {
        return mNotesDao.getAllNotes();
    }

    public LiveData<Note> getNoteById(int id) { return mNotesDao.getNoteById(id); }

    public void insert(final Note note) {
        executorService.execute(() -> mNotesDao.insert(note));
    }

    public void update(final Note note) {
        executorService.execute(() -> mNotesDao.update(note));
    }

    public void delete(final Note note) { executorService.execute(() -> mNotesDao.delete(note));}
}
