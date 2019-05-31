package com.example.lab_3.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.lab_3.database.data.Student;
import com.example.lab_3.utils.FioGenerator;

import java.util.Arrays;
import java.util.List;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class DataBaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "testBase.db";
    private static final int DATABASE_VERSION = 1;

    static {
        cupboard().register(Student.class);
    }

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables();
        Log.e("Database status", "CREATED");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        cupboard().withDatabase(db).upgradeTables();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onOpen(SQLiteDatabase db) {
        cupboard().withDatabase(db).query(Student.class).list().forEach(student -> cupboard().withDatabase(db).delete(Student.class, student.get_id()));
        cupboard().withDatabase(db).put(Arrays.asList(
                new Student(FioGenerator.generateFio()),
                new Student(FioGenerator.generateFio()),
                new Student(FioGenerator.generateFio()),
                new Student(FioGenerator.generateFio()),
                new Student(FioGenerator.generateFio())
                )
        );
        Log.e("Database status", "VERSION: " + db.getVersion());
    }

    public static List<Student> getStudentList(SQLiteDatabase db) {
        return cupboard().withDatabase(db).query(Student.class).list();
    }
}
