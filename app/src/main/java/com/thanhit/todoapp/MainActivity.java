package com.thanhit.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.thanhit.todoapp.adapter.Adapter;
import com.thanhit.todoapp.db.DBHelper;
import com.thanhit.todoapp.model.Note;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.listItem)
    ListView lst;

    Adapter adapter;
    List<Note> listNotes;

    DBHelper dbHelper;

    @BindView(R.id.toolBar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.ic_left);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        dbHelper = new DBHelper(this);
        listNotes = new ArrayList<>();

        listNotes = dbHelper.getAllNote();
        adapter = new Adapter(this,listNotes);
        lst.setAdapter(adapter);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("item",listNotes.get(position));

                Intent intent = new Intent(getApplicationContext(),DetailItemActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_add_item) {
            startActivity(new Intent(getApplicationContext(),CreateItemActivity.class));
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        listNotes = dbHelper.getAllNote();
        adapter.notifyDataSetChanged();
    }


}
