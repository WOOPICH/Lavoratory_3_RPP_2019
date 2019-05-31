package com.example.lab_3;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.lab_3.database.DataBaseHandler;
import com.example.lab_3.adapters.CustomAdapter;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private SQLiteDatabase db = MainActivity.getDb();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initializeComp();
    }

    private void initializeComp() {
        listView = findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(this, DataBaseHandler.getStudentList(db));
        listView.setAdapter(adapter);
    }
}
