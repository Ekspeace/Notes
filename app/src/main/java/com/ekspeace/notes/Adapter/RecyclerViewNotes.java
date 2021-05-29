package com.ekspeace.notes.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.text.MessagePattern;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.recyclerview.widget.RecyclerView;

import com.ekspeace.notes.Model.Note;
import com.ekspeace.notes.Model.PopUp;
import com.ekspeace.notes.R;
import com.ekspeace.notes.View.UpdateNote;
import com.ekspeace.notes.ViewModel.NoteViewModel;

import java.util.List;

public class RecyclerViewNotes extends RecyclerView.Adapter<RecyclerViewNotes.MyViewHolder>  implements PopupMenu.OnMenuItemClickListener {
    private Context context;
    private List<Note> List;
    private OnItemClickListener onItemClickListener;
    private int Position;
    private NoteViewModel noteViewModel;
    private View layout;

    public RecyclerViewNotes(Context context, List<Note> List, NoteViewModel noteViewModel, View layout) {
        this.context = context;
        this.List = List;
        this.noteViewModel = noteViewModel;
        this.layout = layout;
    }

    @NonNull
    @Override
    public RecyclerViewNotes.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.layout_recycler_view_note, parent, false);
        return new RecyclerViewNotes.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewNotes.MyViewHolder holder, int position) {
        String message = List.get(position).getMessage();
        if(message.length() > 35){
            message = message.substring(0, 35);
            holder.note_text.setText(message.concat(" ..."));
        }else {
            holder.note_text.setText(message);
        }
        holder.note_menu.setOnClickListener(v -> {
            Position = holder.getLayoutPosition();
            Context wrapper = new ContextThemeWrapper(context, R.style.PopupMenu);
            PopupMenu popupMenu = new PopupMenu(wrapper, v);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.inflate(R.menu.popup_menu);
            popupMenu.show();

        });
    }


    @Override
    public int getItemCount() {
        return List.size();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit_note:
                Intent intent = new Intent(context, UpdateNote.class);
                intent.putExtra("MESSAGE", List.get(Position).getMessage());
                intent.putExtra("ID", List.get(Position).getId());
                context.startActivity(intent);
                return true;
            case R.id.delete_note:
                PopUp.DeleteDialog(context,"Delete Task", "Are you sure, you want to delete this task ?", noteViewModel, List, Position, layout);
                return true;
            default:
                return false;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView note_text;
        private ImageView note_menu;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            note_text = itemView.findViewById(R.id.note_text);
            note_menu = itemView.findViewById(R.id.note_menu);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                int position = MyViewHolder.this.getBindingAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener.onItemClick(itemView, position);
                }
            }
        }
    }
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
