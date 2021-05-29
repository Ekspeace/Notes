package com.ekspeace.notes.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ekspeace.notes.Adapter.RecyclerViewNotes;
import com.ekspeace.notes.Model.Note;
import com.ekspeace.notes.R;
import com.ekspeace.notes.ViewModel.NoteViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView new_button = findViewById(R.id.new_note);
        TextView text_date = findViewById(R.id.text_date);
        TextView no_notes_text = findViewById(R.id.no_notes);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        NoteViewModel noteViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(NoteViewModel.class);

        new_button.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, NewNote.class)));
        SetDate(text_date);
        PopulateRecyclerView(noteViewModel,recyclerView, no_notes_text);
    }

    private void SetDate(TextView text){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, MMMM dd");

        String Date = simpleDateFormat.format(date);
        text.setText(Date);
    }
    private void PopulateRecyclerView(NoteViewModel noteViewModel, RecyclerView recyclerView, TextView textView){
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_toast_container));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteViewModel.getAllNotes().observe(this, (Observer<List<Note>>) notes -> {
            if(notes.isEmpty()){
                textView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }else{
                textView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
            RecyclerViewNotes adapter = new RecyclerViewNotes(this, notes, noteViewModel, layout);
            recyclerView.setAdapter(adapter);

            adapter.setOnItemClickListener((view, position) -> {
                String Message = notes.get(position).getMessage();
                String Date = notes.get(position).getDate();
                int Id = notes.get(position).getId();
                Intent intent = new Intent(getBaseContext(), ViewNote.class);
                intent.putExtra("KEY_ID", Id);
                intent.putExtra("NOTE_MESSAGE", Message);
                intent.putExtra("NOTE_DATE", Date);
                startActivity(intent);
            });
        });


    }
}