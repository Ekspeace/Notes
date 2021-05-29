package com.ekspeace.notes.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
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

public class NewNote extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        ImageView add_button = findViewById(R.id.add_note);
        TextInputEditText new_note = findViewById(R.id.new_note_massage);

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_container));
        NoteViewModel noteViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(NoteViewModel.class);

        add_button.setOnClickListener(v -> AddNote(new_note, noteViewModel, layout));
    }
    private void AddNote(TextInputEditText editText, NoteViewModel noteViewModel, View view){
        String message = editText.getText().toString().trim();

        if (TextUtils.isEmpty(message)) {
            editText.setError("Please enter a task");
            return;
        }
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MMMM dd");
        String Date = simpleDateFormat.format(date);

        Note note = new Note();
        note.setMessage(message);
        note.setDate(Date);
        noteViewModel.insert(note);
        PopUp.Toast(this, view, "A task successfully added", Toast.LENGTH_SHORT);
        startActivity(new Intent(this, MainActivity.class));
    }
}