package com.hacktiv8.todolist.ui;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hacktiv8.todolist.data.NoteRepository;
import com.hacktiv8.todolist.data.model.Note;

import java.util.List;

public class MainViewModel extends ViewModel {
    private final NoteRepository mNoteRepository;

    public MainViewModel(Application application) {
        mNoteRepository = new NoteRepository(application);
    }

    LiveData<List<Note>> getAllNotes() {
        return mNoteRepository.getAllNotes();
    }

    public void update(Note note) {
        mNoteRepository.update(note);
    }



}
