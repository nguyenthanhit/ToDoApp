package com.thanhit.todoapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.thanhit.todoapp.model.Note;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Admin on 4/27/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public final  static String DATABSE_NAME = "DdbToDo" ;
    public final  static String TABLE_NAME = "Note" ;
    private static final int DATABASE_VERSION = 1;

    public final  static String ID = "id" ;
    public final  static String TASK_NAME = "TaskName" ;
    public final  static String TASK_NOTE = "TaskNote" ;
    public final  static String DUE_DATE = "DueDate" ;
    public final  static String PRIORITY = "Priority" ;
    public final  static String STATUS = "Status" ;

    public DBHelper(Context context) {
        super(context,DATABSE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + TASK_NAME + " TEXT,"
                + DUE_DATE + " TEXT,"
                + TASK_NOTE + " TEXT,"
                + PRIORITY + " TEXT,"
                + STATUS + " TEXT )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public List<Note> getAllNote() {
        List<Note> listNotes = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Note note = new Note(cursor.getInt(0), cursor.getString(1), cursor.getString(2)
                        , cursor.getString(3), cursor.getString(4), cursor.getString(5));
                listNotes.add(note);
            } while (cursor.moveToNext());
        }

        return listNotes;
    }
    public  int Insert(Note note) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TASK_NAME,note.getTaskName());
        values.put(DUE_DATE,note.getDueDate());
        values.put(TASK_NOTE,note.getTaskNote());
        values.put(PRIORITY,note.getPriority());
        values.put(STATUS,note.getStatus());

        int result = (int) db.insert(TABLE_NAME,null,values);
        db.close();
        return result;
    }

    public int Update(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TASK_NAME,note.getTaskName());
        values.put(DUE_DATE,note.getDueDate());
        values.put(TASK_NOTE,note.getTaskNote());
        values.put(PRIORITY,note.getPriority());
        values.put(STATUS,note.getStatus());

        return db.update(TABLE_NAME,values, ID + " =?",new String[]{String.valueOf(note.getId())} );
    }

    public int Delete(Note note) {
        SQLiteDatabase db = getWritableDatabase();
        int result = db.delete(TABLE_NAME,ID +" =?",new String[]{String.valueOf(note.getId())});
        db.close();
        return result;
    }
}
