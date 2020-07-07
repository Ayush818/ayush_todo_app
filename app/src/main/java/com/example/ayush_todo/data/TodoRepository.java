package com.example.ayush_todo.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.ayush_todo.model.ETodo;

import java.util.List;

public class TodoRepository {

    private TodoDAO mTodoDAO;
    private LiveData<List<ETodo>> mAllTodoList;

    public TodoRepository(Application application) {
            TodoRoomDatabase database = TodoRoomDatabase.getDatabase(application);
            mTodoDAO=database.mTodoDAO();
            mAllTodoList = mTodoDAO.getAllTodos();

    }

    public LiveData<List<ETodo>> getAllTodoList() {
        return mAllTodoList;
    }

    public ETodo getTodoById(int id){
        return mTodoDAO.getTodoById(id);
    }


    public void insert(ETodo todo){
            new insertTodoAsynchTask(mTodoDAO).execute(todo);
    }
    private static class insertTodoAsynchTask extends AsyncTask<ETodo,Void,Void>

    {
        private TodoDAO mTodoDAO;
        private insertTodoAsynchTask(TodoDAO todoDAO)
        {
            mTodoDAO=todoDAO;
        }

        @Override
        protected Void doInBackground(ETodo... todos) {
            mTodoDAO.insert(todos[0]);
            return null;
        }
    }

    public void deleteAll()
    {
        new deleteAllTodoAsyncTask(mTodoDAO).execute();
    }
    private  static class deleteAllTodoAsyncTask extends AsyncTask<ETodo,Void,Void>
    {
        private TodoDAO mTodoDAO;
        private deleteAllTodoAsyncTask(TodoDAO todoDAO)
        {
            mTodoDAO=todoDAO;
        }
        @Override
        protected Void doInBackground(ETodo... todo) {
            mTodoDAO.deleteAll();
            return null;
        }
    }


    public void update(ETodo todo){
        new updateTodoAsynchTask(mTodoDAO).execute(todo);
    }
    private static class updateTodoAsynchTask extends AsyncTask<ETodo,Void,Void>
    {
        private TodoDAO mTodoDAO;
        private updateTodoAsynchTask(TodoDAO todoDAO)
        {
            mTodoDAO=todoDAO;
        }

        @Override
        protected Void doInBackground(ETodo... todos) {
            mTodoDAO.update(todos[0]);
            return null;
        }
    }


    public void deleteById(ETodo Todo)
    {
        new deleteByIdTodoAsyncTask(mTodoDAO).execute(Todo);
    }
    private  static class deleteByIdTodoAsyncTask extends AsyncTask<ETodo,Void,Void>
    {
        private TodoDAO mTodoDAO;
        private deleteByIdTodoAsyncTask(TodoDAO todoDAO)
        {
            mTodoDAO=todoDAO;
        }
        @Override
        protected Void doInBackground(ETodo... todo) {
            mTodoDAO.deleteById(todo[0]);
            return null;
        }
    }



  public ETodo getTodo(int id){
        return mTodoDAO.getTodoById(id);
  }
}
