package com.thanhit.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.thanhit.todoapp.db.DBHelper;
import com.thanhit.todoapp.model.Note;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.id.edit;
import static android.icu.text.UnicodeSet.CASE;

public class EditItemActivity extends AppCompatActivity {

    @BindView(R.id.spPriority)
    Spinner spPriority;

    @BindView(R.id.spStatus)
    Spinner spStatus;

    @BindView(R.id.edtTaskName)
    EditText edtTaskName;

    @BindView(R.id.edtTaskNote)
    EditText edtTaskNote;

    @BindView(R.id.datePicker)
    DatePicker datePicker;

    String [] listPriority;
    ArrayAdapter<String> arrayAdapter;
    ArrayAdapter<String> statusAdapter;
    String [] listStatus;

    @BindView(R.id.toolBar)
    Toolbar toolbar;

    Bundle bundle;
    Note note;
    DBHelper db;
    Calendar  cal = Calendar.getInstance();
    SimpleDateFormat dateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.ic_left);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        db = new DBHelper(this);
        dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        listPriority = getResources().getStringArray(R.array.listprority);
        listStatus = getResources().getStringArray(R.array.listStatus);

        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listPriority);
        statusAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,listStatus);
        spPriority.setAdapter(arrayAdapter);
        spStatus.setAdapter(statusAdapter);


        bundle = getIntent().getExtras();
        note = (Note) bundle.getSerializable("item");

        edtTaskName.setText(note.getTaskName());
        edtTaskName.setSelection(edtTaskName.getText().length());
        edtTaskNote.setText(note.getTaskNote());
        edtTaskNote.setSelection(edtTaskNote.getText().length());

        try {
            cal.setTime(dateFormat.parse(note.getDueDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        datePicker.updateDate(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH));

        for (int i=0; i <listPriority.length; i++) {
            if (note.getPriority().equals(listPriority[i])) {
                spPriority.setSelection(i);
            }
        }

        for (int i=0; i <listStatus.length; i++) {
            if (note.getPriority().equals(listStatus[i])) {
                spStatus.setSelection(i);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                note.setTaskName(edtTaskName.getText().toString());
                note.setTaskNote(edtTaskNote.getText().toString());
                note.setPriority(spPriority.getSelectedItem().toString());
                note.setStatus(spStatus.getSelectedItem().toString());

                cal.set(Calendar.YEAR,datePicker.getYear());
                cal.set(Calendar.MONTH,datePicker.getMonth());
                cal.set(Calendar.DAY_OF_MONTH,datePicker.getDayOfMonth());
                String date = dateFormat.format(cal.getTime());

                note.setDueDate(date);
                if(db.Update(note) > 0) {
                    Intent intent = new Intent(getApplicationContext(),DetailItemActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
            case R.id.menu_close:
               startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;
        }

        return true;
    }


}
