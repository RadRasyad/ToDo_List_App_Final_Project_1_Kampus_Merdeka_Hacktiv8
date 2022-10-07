package com.hacktiv8.todolist.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.hacktiv8.todolist.data.model.Note;
import com.hacktiv8.todolist.databinding.NoteItemBinding;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private final ArrayList<Note> listNotes = new ArrayList<>();

    public NoteAdapter(EditItemListener editItemListener) {
        this.editItemListener = editItemListener;
    }

    void setListNotes(List<Note> listNotes) {
        this.listNotes.clear();
        this.listNotes.addAll(listNotes);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NoteItemBinding binding = NoteItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = listNotes.get(position);
        holder.bind(note);
    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final NoteItemBinding binding;
        private TextView noteTv;
        private TextView descTv;
        private CheckBox ketCb;

        public ViewHolder(NoteItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Note note) {
            binding.titleTv.setText(note.getTitle());
            binding.deskTv.setText(note.getDescription());

            binding.ketCbox.setOnClickListener(v -> {
                editItemListener.onEditItemListener(getAdapterPosition());
            });
        }
    }

    private EditItemListener editItemListener;

    public interface EditItemListener {
        void onEditItemListener(int position);
    }
}
