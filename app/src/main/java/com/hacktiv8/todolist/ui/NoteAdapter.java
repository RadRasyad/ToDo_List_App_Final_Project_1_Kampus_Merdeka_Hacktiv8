package com.hacktiv8.todolist.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hacktiv8.todolist.R;
import com.hacktiv8.todolist.data.model.Note;
import com.hacktiv8.todolist.utils.NoteDiffCallback;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private ArrayList<Note> listNotes = new ArrayList<>();

    void setListNotes(List<Note> listNotes) {
        final NoteDiffCallback diffCallback = new NoteDiffCallback(this.listNotes, listNotes);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.listNotes.clear();
        this.listNotes.addAll(listNotes);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = listNotes.get(position);
        holder.noteTv.setText(note.getTitle());
        holder.descTv.setText(note.getDescription());

    }

    @Override
    public int getItemCount() {
        return listNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView noteTv;
        private TextView descTv;
//        private CheckBox ketCb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            noteTv = itemView.findViewById(R.id.title_tv);
            descTv = itemView.findViewById(R.id.desc_iv);
//            ketCb  = itemView.findViewById(R.id.ket_cbox);
        }
    }
}
