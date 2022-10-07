package com.hacktiv8.todolist.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.hacktiv8.todolist.R;
import com.hacktiv8.todolist.databinding.ActivityMainBinding;
import com.hacktiv8.todolist.utils.ViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private RecyclerView noteRv;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);

        MainViewModel mainViewModel = obtainViewModel(MainActivity.this);
        mainViewModel.getAllNotes().observe(this, notes -> {
            if (notes != null) {
                noteAdapter.setListNotes(notes);
            }
        });

        noteRv = binding.noteRv;
        noteAdapter = new NoteAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        noteRv.setLayoutManager(layoutManager);
        noteRv.setHasFixedSize(true);
        noteRv.setAdapter(noteAdapter);

    }

    @NonNull
    private static MainViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, (ViewModelProvider.Factory) factory).get(MainViewModel.class);
    }
}