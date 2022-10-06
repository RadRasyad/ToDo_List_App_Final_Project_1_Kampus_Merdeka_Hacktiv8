package com.hacktiv8.todolist;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import com.hacktiv8.todolist.data.NoteRepository;
import com.hacktiv8.todolist.data.model.Note;

public class AddUpdateViewModel extends ViewModel {
    private final NoteRepository mNoteRepository;

    public AddUpdateViewModel(Application application) {
        mNoteRepository = new NoteRepository(application);
    }

    public void insert(Note note) {
        mNoteRepository.insert(note);
    }

    public void update(Note note) {
        mNoteRepository.update(note);
    }

//    public void delete(Note note) {
//        mNoteRepository.delete(note);
//    }

}
