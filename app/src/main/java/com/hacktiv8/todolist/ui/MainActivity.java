package com.hacktiv8.todolist.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.hacktiv8.todolist.R;
import com.hacktiv8.todolist.data.model.Note;
import com.hacktiv8.todolist.databinding.ActivityMainBinding;
import com.hacktiv8.todolist.utils.ViewModelFactory;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteAdapter.EditItemListener {

    private ActivityMainBinding binding;

    private MainViewModel mainViewModel;
    private RecyclerView noteRv;
    private NoteAdapter noteAdapter;
    private List<Note> listNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        noteRv = binding.noteRv;
        noteAdapter = new NoteAdapter(this);

        mainViewModel = obtainViewModel(MainActivity.this);
        mainViewModel.getAllNotes().observe(this, notes -> {
            if (notes != null) {
                listNote = notes;
                noteAdapter.setListNotes(notes);
                noteAdapter.notifyDataSetChanged();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        noteRv.setLayoutManager(layoutManager);
        noteRv.setHasFixedSize(true);
        noteRv.setAdapter(noteAdapter);

        binding.fabAdd.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddNoteActivity.class);
            startActivity(intent);
        });

    }

    @NonNull
    private static MainViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, (ViewModelProvider.Factory) factory).get(MainViewModel.class);
    }

    @Override
    public void onEditItemListener(int position) {
        Note note = listNote.get(position);
        boolean isDone = note.getDone();
        Log.d("Main Activity", String.valueOf(isDone));
        if (isDone == true) {
            isDone = false;
        } else {
            isDone = true;
        }
        note.setDone(isDone);
        mainViewModel.update(note);
    }

}