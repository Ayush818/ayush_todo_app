package com.example.ayush_todo.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ayush_todo.model.ETodo;

import java.util.List;

@Dao
public interface TodoDAO {
    @Insert
    void insert (ETodo todo);

    @Delete
    void deleteById(ETodo todo);

    @Query("DELETE FROM Notes_table")
    void  deleteAll();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(ETodo... todos);

    @Query("SELECT * FROM Notes_table ORDER BY todo_date,priority desc")
    LiveData<List<ETodo>> getAllTodos();

    @Query("SELECT * FROM Notes_table WHERE id=:id")
    ETodo getTodoById(int id);





}
