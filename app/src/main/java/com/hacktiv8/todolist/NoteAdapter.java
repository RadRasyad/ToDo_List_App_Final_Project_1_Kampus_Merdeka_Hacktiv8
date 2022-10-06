package com.hacktiv8.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hacktiv8.todolist.data.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private Context context;

    private List<Note> noteList = new ArrayList<>();

    public NoteAdapter(Context context, List<Note> noteList){
        this.context = context;
        this.noteList = noteList;
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
        Note note = noteList.get(position);
        holder.noteTv.setText(note.getTitle());
        holder.descTv.setText(note.describeContents());

    }

    @Override
    public int getItemCount() {
        return noteList.size();
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
