package com.ekspeace.notes.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ekspeace.notes.Model.Note;
import com.ekspeace.notes.R;
import com.ekspeace.notes.ViewModel.NoteViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ViewNote extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_note);
        String message = getIntent().getStringExtra("NOTE_MESSAGE");
        String date = getIntent().getStringExtra("NOTE_DATE");
        int id = getIntent().getIntExtra("KEY_ID", 0);

        ImageView alter_button = findViewById(R.id.alter_note);
        TextView view_note = findViewById(R.id.view_note_massage);
        TextView view_date = findViewById(R.id.view_date_text);

        view_note.setText(message);
        view_date.setText(date);

        alter_button.setOnClickListener(v -> {
            Intent intent = new Intent(ViewNote.this, UpdateNote.class);
            intent.putExtra("ID", id);
            intent.putExtra("MESSAGE", message);
            startActivity(intent);
        });
    }
}