package com.example.lab_3.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lab_3.R;
import com.example.lab_3.database.data.Student;

import java.text.SimpleDateFormat;
import java.util.List;


public class CustomAdapter extends ArrayAdapter<Student> {

    public CustomAdapter(Context context, List<Student> students) {
        super(context, 0, students);
    }


    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Student student = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        final TextView idView = convertView.findViewById(R.id.Id);
        final TextView fioView = convertView.findViewById(R.id.FIO);
        final TextView dateView = convertView.findViewById(R.id.dateJoined);
        idView.setText(student.get_id().toString());
        fioView.setText(student.getSecondName() + " " + student.getFirstName() + " " + student.getMiddleName());
        dateView.setText(new SimpleDateFormat("MM-dd HH:mm:ss").format(student.getDateJoined()));
        return convertView;
    }
}
