package com.ekspeace.notes.Interface;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ekspeace.notes.Model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void Insert(Note note);

    @Update
    void Update(Note note);

    @Delete
    void Delete(Note note);

    @Query("SELECT * FROM notes_table ORDER BY Id DESC")
    LiveData<List<Note>> getAllNotes();
}
