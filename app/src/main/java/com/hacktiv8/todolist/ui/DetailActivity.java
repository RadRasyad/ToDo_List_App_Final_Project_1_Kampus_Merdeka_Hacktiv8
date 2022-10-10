package com.hacktiv8.todolist.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.hacktiv8.todolist.R;
import com.hacktiv8.todolist.data.model.Note;
import com.hacktiv8.todolist.databinding.ActivityDetailBinding;
import com.hacktiv8.todolist.utils.ViewModelFactory;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    private DetailViewModel detailViewModel;
    public static final String EXTRA_DATA = "extra_data";
    private int id;
    private Note note;
    private boolean isDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle("Edit Data");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id = getIntent().getIntExtra(EXTRA_DATA, 0);

        detailViewModel = obtainViewModel(DetailActivity.this);
        if (id!=0) {
            Log.d("DetailActivity", String.valueOf(id));
            detailViewModel.getNoteById(id).observe(this, note ->  {
                if (note!=null) {

                    binding.inputTitle.setText(note.getTitle());
                    binding.inputDescription.setText(note.getDescription());
                    isDone = note.getDone();

                    if (isDone==true) {
                        binding.ketCbox.setChecked(true);
                    } else {
                        binding.ketCbox.setChecked(false);
                    }
                }
            });

            binding.ketCbox.setOnClickListener(v -> {
                if (isDone == true) {
                    isDone = false;
                } else {
                    isDone = true;
                }
            });

            binding.btnAdd.setOnClickListener(view -> {
                String title = binding.inputTitle.getText().toString().trim();
                String description = binding.inputDescription.getText().toString().trim();

                boolean isValid = validation(title, description);
                if (isValid) {
                    note = new Note();
                    note.setId(id);
                    note.setTitle(title);
                    note.setDescription(description);
                    note.setDone(isDone);

                    detailViewModel.update(note);
                    Toast.makeText(this, "Data Dirubah", Toast.LENGTH_SHORT).show();

                    finish();
                } else {
                    Toast.makeText(this, "Text Field Masih Kosong", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Gagal mendapatkan data", Toast.LENGTH_SHORT).show();
            finish();
        }
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
    private static DetailViewModel obtainViewModel(AppCompatActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, (ViewModelProvider.Factory) factory).get(DetailViewModel.class);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        setSelected(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void setSelected(int selectedMode) {
        switch (selectedMode) {
            case android.R.id.home:
                showCloseAlertDialog();
                break;
            case R.id.actionDelete:
                //isi dengan action delete
                Toast.makeText(this, "Data Dihapus", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void showCloseAlertDialog() {
        String dialogTitle, dialogMessage;

        dialogTitle = getString(R.string.cancel);
        dialogMessage = getString(R.string.message_cancel);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder
                .setMessage(dialogMessage)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), (dialog, id) -> {
                    finish();
                })
                .setNegativeButton(getString(R.string.no), (dialog, id) -> dialog.cancel());
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}