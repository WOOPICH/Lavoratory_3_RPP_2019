package com.example.lab_3.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.lab_3.database.data.Student;
import com.example.lab_3.utils.FioGenerator;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class DataBaseHandler extends SQLiteOpenHelper {
    Context context;
    private static final String DATABASE_NAME = "testBase.db";
    private static final int DATABASE_VERSION = 2;

    static {
        cupboard().register(Student.class);
    }

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        cupboard().withDatabase(db).createTables();
        Log.e("Database status", "CREATED");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        LinkedHashMap<Long, String> fio = new LinkedHashMap<>();
        Cursor cursor = db.rawQuery("SELECT fio,_id FROM Student", null);
        cursor.moveToFirst();
        fio.put(cursor.getLong(1), cursor.getString(0));
        while (cursor.moveToNext()) {
            fio.put(cursor.getLong(1), cursor.getString(0));
        }

        cupboard().withDatabase(db).upgradeTables();

        fio.forEach((id, s) -> {
            String[] fioSeparetad = s.split(" ");
            ContentValues values = new ContentValues(3);
            values.put("firstName", fioSeparetad[1]);
            values.put("secondName", fioSeparetad[0]);
            values.put("middleName", fioSeparetad[2]);
            cupboard().withDatabase(db).update(Student.class, values, "_id=?", id.toString());
        });
        Log.e("Database status", "MIGRATE: " + db.getVersion());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onOpen(SQLiteDatabase db) {
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
