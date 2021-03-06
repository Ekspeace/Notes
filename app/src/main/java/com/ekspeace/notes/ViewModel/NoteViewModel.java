package com.ekspeace.notes.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ekspeace.notes.Model.Note;
import com.ekspeace.notes.Repository.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;
    public NoteViewModel(Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }
    public void insert(Note note) {
        repository.insert(note);
    }
    public void delete(Note note) {
        repository.delete(note);
    }
    public void update(Note note) {
        repository.update(note);
    }
    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
