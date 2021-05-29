package com.ekspeace.notes.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ekspeace.notes.Model.Note;
import com.ekspeace.notes.Model.PopUp;
import com.ekspeace.notes.R;
import com.ekspeace.notes.ViewModel.NoteViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UpdateNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note);
        String message_update = getIntent().getStringExtra("MESSAGE");

        ImageView save_button = findViewById(R.id.save_note);
        TextInputEditText update_note = findViewById(R.id.update_note_massage);

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_container));
        NoteViewModel noteViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(NoteViewModel.class);

        update_note.setText(message_update);

        save_button.setOnClickListener(v -> UpdateNote(update_note, noteViewModel, layout));
    }

    private void UpdateNote(TextInputEditText editText, NoteViewModel noteViewModel, View view){
        int id = getIntent().getIntExtra("ID", 1);
        String message = editText.getText().toString().trim();

        if (TextUtils.isEmpty(message)) {
            editText.setError("Please enter a task");
            return;
        }
            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MMMM dd");
            String Date = simpleDateFormat.format(date);

            Note note = new Note(message, id, Date);
            noteViewModel.update(note);
            PopUp.Toast(this, view, "A task successfully updated", Toast.LENGTH_SHORT);
            startActivity(new Intent(UpdateNote.this, MainActivity.class));
    }
}