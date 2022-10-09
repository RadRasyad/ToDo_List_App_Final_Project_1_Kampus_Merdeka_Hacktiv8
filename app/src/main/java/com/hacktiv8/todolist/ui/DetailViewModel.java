package com.hacktiv8.todolist.ui;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.hacktiv8.todolist.data.NoteRepository;
import com.hacktiv8.todolist.data.model.Note;

public class DetailViewModel extends ViewModel {
    private final NoteRepository mNoteRepository;

    public DetailViewModel(Application application) {
        mNoteRepository = new NoteRepository(application);
    }

    public LiveData<Note> getNoteById(int id) {
        return mNoteRepository.getNoteById(id);
    }

    public void update(Note note) {
        mNoteRepository.update(note);
    }

}
