package com.example.ayush_todo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.ayush_todo.data.TodoRepository;
import com.example.ayush_todo.model.ETodo;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private TodoRepository mTodoRepository;
    private LiveData<List<ETodo>> mAllTodos ;
    public TodoViewModel(@NonNull Application application) {
        super(application);
        mTodoRepository = new TodoRepository(application);
        mAllTodos = mTodoRepository.getAllTodoList();
    }
    public  void insert(ETodo todo)
    {
        mTodoRepository.insert(todo);
    }
    public ETodo getTodo(int id) {
        return mTodoRepository.getTodo(id);
    }
    public LiveData<List<ETodo>>getmAllTodos(){

           return mAllTodos;
       }

    public void update(ETodo todo){
        mTodoRepository.update(todo);
    }

   public ETodo getTodoById(int id){
       return mTodoRepository.getTodo(id);
    }
    public void deleteById(ETodo todo){
        mTodoRepository.deleteById(todo);


    }
    public void deleteAll()
    {
        mTodoRepository.deleteAll();
    }



}
