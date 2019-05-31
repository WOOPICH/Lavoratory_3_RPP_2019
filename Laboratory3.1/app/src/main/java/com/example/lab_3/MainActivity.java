package com.example.lab_3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.lab_3.database.DataBaseHandler;
import com.example.lab_3.database.data.Student;
import com.example.lab_3.utils.FioGenerator;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class MainActivity extends AppCompatActivity {
    private Button listButton;
    private Button addButton;
    private Button changeButton;
    private Button dropButton;

    private DataBaseHandler dbHelper;
    private static SQLiteDatabase db;

    public static SQLiteDatabase getDb() {
        return db;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeDb();
        initializeComp();
        setAction();
    }

    private void initializeComp() {
        listButton = findViewById(R.id.listButton);
        addButton = findViewById(R.id.addButton);
        changeButton = findViewById(R.id.changeButton);
        dropButton = findViewById(R.id.dropButton);
    }

    private void initializeDb() {
        dbHelper = new DataBaseHandler(this);
        db = dbHelper.getWritableDatabase();
    }


    private void setAction() {
        listButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);
        });
        addButton.setOnClickListener(v -> cupboard().withDatabase(db).put(new Student(FioGenerator.generateFio())));
        changeButton.setOnClickListener(v -> {
            ContentValues values = new ContentValues(3);
            values.put("firstName", "Иван");
            values.put("secondName", "Иванов");
            values.put("middleName", "Иванович");
            cupboard().withDatabase(db).update(Student.class, values, "_id = (SELECT MAX(_id) FROM Student)");
        });
        dropButton.setOnClickListener(v -> {
            this.deleteDatabase("testBase.db");
            listButton.setClickable(false);
            addButton.setClickable(false);
            changeButton.setClickable(false);
        });
    }
}
