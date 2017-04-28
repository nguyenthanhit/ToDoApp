package com.thanhit.todoapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thanhit.todoapp.R;
import com.thanhit.todoapp.model.Note;

import java.util.List;

/**
 * Created by Admin on 4/27/2017.
 */

public class Adapter extends BaseAdapter {

    List<Note> listNote;
    LayoutInflater inflater;

    public Adapter(Context context, List<Note> list) {
        listNote = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return listNote.size();
    }

    @Override
    public Object getItem(int position) {
        return listNote.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.list_item,null);

        TextView txtItem1 = (TextView)view.findViewById(R.id.txtItem1);
        TextView txtItem2 = (TextView)view.findViewById(R.id.txtItem2);

        Note item = listNote.get(position);
        txtItem1.setText(item.getTaskName());

        if(item.getPriority().equals("LOW")) {
            txtItem2.setTextColor(Color.parseColor("#5EBA7D"));
        } else if (item.getPriority().equals("MEDIUM")) {
            txtItem2.setTextColor(Color.parseColor("#D09B4C"));
        } else {
            txtItem2.setTextColor(Color.parseColor("#EA7781"));
        }

        txtItem2.setText(item.getPriority());

        return view;
    }

}
