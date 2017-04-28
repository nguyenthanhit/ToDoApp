package com.thanhit.todoapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.thanhit.todoapp.db.DBHelper;
import com.thanhit.todoapp.model.Note;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.id.edit;

public class DetailItemActivity extends AppCompatActivity {

    @BindView(R.id.txtDuedate)
    TextView txtDate;

    @BindView(R.id.txtName)
    TextView txtName;

    @BindView(R.id.txtStatus)
    TextView txtStatus;

    @BindView(R.id.txtPriority)
    TextView txtPriority;

    @BindView(R.id.txtTakeNote)
    TextView txtTaskNote;

    @BindView(R.id.toolBar)
    Toolbar toolbar;
    Note note;
    Bundle bundle;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_item);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setLogo(R.drawable.ic_left);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.left_arrow);

        dbHelper = new DBHelper(this);
        bundle = getIntent().getExtras();
        note = (Note) bundle.getSerializable("item");

        txtDate.setText(note.getDueDate());
        txtName.setText(note.getTaskName());
        txtPriority.setText(note.getPriority());
        txtStatus.setText(note.getStatus());
        txtTaskNote.setText(note.getTaskNote());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId() ) {
            case android.R.id.home:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;
            case R.id.menu_edit:
                Intent intent = new Intent(getApplicationContext(),EditItemActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;

            case R.id.menu_delete:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Are you want to delete ?");
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.Delete(note);
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    }
                });
               dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                   }
               });
                dialog.show();
                break;

        }

        return true;
    }
}
