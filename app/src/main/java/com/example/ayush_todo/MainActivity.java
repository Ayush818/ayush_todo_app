package com.example.ayush_todo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.ayush_todo.R;

import com.example.ayush_todo.viewmodel.TodoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
        FloatingActionButton fabnew;
        Fragment mfragment;
        FragmentManager mfragmentManager;
        TodoViewModel mTodoViewModel;
        AlertDialog.Builder mAlertdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mfragment= new ViewDataFragment();
        mfragmentManager=getSupportFragmentManager();
        mfragmentManager.beginTransaction().add(R.id.list_container,mfragment).commit();

        fabnew =findViewById(R.id.fab_add_view);
        fabnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(MainActivity.this,EditTodoActivity.class);
                startActivity(intent);

            }
        });
        mTodoViewModel= ViewModelProviders.of(this).get(TodoViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all_menu:
                mAlertdialog = new AlertDialog.Builder(MainActivity.this);
                mAlertdialog.setMessage(getString(R.string.delete_all)).setCancelable(false).setTitle(getString(R.string.app_name)).setIcon(R.mipmap.ic_launcher);
                mAlertdialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mTodoViewModel.deleteAll();

                    }
                });
                mAlertdialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                mAlertdialog.show();
                break;
            case R.id.logout_menu:
                mAlertdialog = new AlertDialog.Builder(MainActivity.this);
                mAlertdialog.setMessage(getString(R.string.logout_app)).setCancelable(false).setTitle(getString(R.string.app_name)).setIcon(R.mipmap.ic_launcher);
                mAlertdialog.setPositiveButton(getString(R.string.btn_logout), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences preferences = getApplicationContext().getSharedPreferences("todo_pref ",0);
                        SharedPreferences.Editor editor= preferences.edit();
                        editor.clear();
                        editor.commit();
                        Intent intent= new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });
                mAlertdialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                mAlertdialog.show();



                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
