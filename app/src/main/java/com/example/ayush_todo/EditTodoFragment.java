package com.example.ayush_todo;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ayush_todo.R;

import com.example.ayush_todo.model.ETodo;
import com.example.ayush_todo.viewmodel.TodoViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class EditTodoFragment extends Fragment {

    View  rootView;

    EditText textTitle,textDesciption, txtDate;
    RadioGroup rgpriority;
    RadioButton rhigh,rmedium,rlow, selectedrb;
    CheckBox checkcomplete;
    Button savebtn,cancelbtn;

    AlertDialog.Builder mAlertdialog;
    DatePickerDialog mDatePicker;
    private int todoid;

    public static final int HIGH_PRIORITY=1;
    public static final int MEDIUM_PRIORITY=2;
    public static final int LOW_PRIORITY=3;

    private TodoViewModel mTodoViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_edit_todo, container, false);
        mTodoViewModel = ViewModelProviders.of(this).get(TodoViewModel.class);


        textTitle = rootView.findViewById(R.id.edit_title);
        textDesciption = rootView.findViewById(R.id.edit_description);
        rgpriority = rootView.findViewById(R.id.edit_priority);
        rhigh = rootView.findViewById(R.id.edit_rb_high);
        rmedium = rootView.findViewById(R.id.edit_rb_medium);
        rlow = rootView.findViewById(R.id.edit_rb_low);
        checkcomplete = rootView.findViewById(R.id.edit_checkbox);
        savebtn = rootView.findViewById(R.id.edit_save);


        txtDate = rootView.findViewById(R.id.edit_date);

        cancelbtn = rootView.findViewById(R.id.edit_cancel);


        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveToDo();
            }
        });


        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayAlertDialog();
            }
        });
        txtDate.setOnTouchListener(new View.OnTouchListener() {
            @Override


            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    DisplayDate();
                }
                return false;
            }
        });
        todoid = getActivity().getIntent().getIntExtra("todo_id", -1);
        if (todoid != -1) {
            savebtn.setText(R.string.data_update);
            ETodo todo = mTodoViewModel.getTodoById(todoid);
            textTitle.setText(todo.getTitle());
            textDesciption.setText(todo.getDescription());
            DateFormat formattor;
            formattor = new SimpleDateFormat("yyyy-MM-dd");
            txtDate.setText(formattor.format(todo.getTodo_date()));
            switch (todo.getPriority()) {
                case 1:
                    rgpriority.check(R.id.edit_rb_high);
                    break;
                case 2:
                    rgpriority.check(R.id.edit_rb_medium);
                    break;
                case 3:
                    rgpriority.check(R.id.edit_rb_low);
                    break;
            }

            checkcomplete.setSelected(todo.isIs_completed());
        }
            return rootView;
        }


    void DisplayAlertDialog(){
        mAlertdialog = new AlertDialog.Builder(getContext());
        mAlertdialog.setMessage(getString(R.string.cancel_alert)).setCancelable(false).setTitle(getString(R.string.app_name)).setIcon(R.mipmap.ic_launcher);
        mAlertdialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);

            }
        });
        mAlertdialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        mAlertdialog.show();
    }

    void DisplayDate(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        mDatePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            txtDate.setText(year+"-"+month+"-"+dayOfMonth);
            }
        },year,month,day);
        mDatePicker.show();

    }


    void SaveToDo()
    {
        ETodo todo = new ETodo();
        Date todoDate;
        int priority=1;
        int checkedpriority =-1;

        todo.setTitle(textTitle.getText().toString());
        todo.setDescription(textDesciption.getText().toString());
        try{
            DateFormat formattor;
            formattor = new SimpleDateFormat("yyyy-MM-dd");
            todoDate=(Date)formattor.parse(txtDate.getText().toString());
                    todo.setTodo_date((todoDate));
        }
        catch(ParseException e)

        {
            e.printStackTrace();
        }
            checkedpriority=rgpriority.getCheckedRadioButtonId();
            switch(checkedpriority)
            {
                case R.id.edit_rb_high:
                        priority=HIGH_PRIORITY;
                        break;
                case R.id.edit_rb_medium:
                        priority=MEDIUM_PRIORITY;
                        break;
                case R.id.edit_rb_low:
                    priority=LOW_PRIORITY;
                    break;

            }

            todo.setPriority(priority);
            todo.setIs_completed(checkcomplete.isChecked());
                if(todoid!= -1){
                    todo.setId(todoid);
                    mTodoViewModel.update(todo);
                    Toast.makeText(getActivity(),getText(R.string.data_updated),Toast.LENGTH_SHORT).show();

                }
                else {
                    mTodoViewModel.insert(todo);
                    Toast.makeText(getActivity(), getText(R.string.data_saved), Toast.LENGTH_SHORT).show();
                }
        Intent intent = new Intent(getActivity(),MainActivity.class);
        startActivity(intent);
    }
}
