package com.hacktiv8.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.hacktiv8.todolist.data.model.Note;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView noteRv;

    List<Note> noteList;
    NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteRv = findViewById(R.id.note_rv);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        noteRv.setLayoutManager(layoutManager);




    }
}