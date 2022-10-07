package com.hacktiv8.todolist.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hacktiv8.todolist.data.model.Note;
import com.hacktiv8.todolist.databinding.ActivityAddNoteBinding;
import com.hacktiv8.todolist.utils.ViewModelFactory;

public class AddNoteActivity extends AppCompatActivity {

    private ActivityAddNoteBinding binding;
    private AddViewModel addViewModel;
    private Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("Tambah Data");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addViewModel = obtainViewModel(AddNoteActivity.this);

        binding.btnAdd.setOnClickListener(view -> {
            String title = binding.inputTitle.getText().toString().trim();
            String description = binding.inputDescription.getText().toString().trim();

            boolean isValid = validation(title, description);
            if (isValid) {
                note = new Note();
                note.setTitle(title);
                note.setDescription(description);
                note.setDone(false);

                addViewModel.insert(note);
                Toast.makeText(this, "Data Ditambahkan", Toast.LENGTH_SHORT).show();

                finish();
            } else {
                Toast.makeText(this, "Text Field Masih Kosong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validation(String title, String description) {

        if ("".equals(title) && "".equals(description)) {
            binding.inputTitle.setError("Kosong");
            binding.inputDescription.setError("Kosong");
            return false;
        } else if ("".equals(title)) {
            binding.inputTitle.setError("Kosong");
            return false;
        } else if ("".equals(description)) {
            binding.inputDescription.setError("Kosong");
            return false;
        } else {
            return true;
        }
    }

    @NonNull
    private static AddViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, (ViewModelProvider.Factory) factory).get(AddViewModel.class);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        return true;
    }
}