package com.polaris.empmgtsystem.DBManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.polaris.empmgtsystem.Model.Employee;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {
    // Database Name
    private static final String DATABASE_NAME = "employee.bd";
    // database version
    private static final int DATABASE_VERSION = 1;
    //  attributes
    private static final String COLUOM_ID = "id";
    private static final String COLUOM_NAME = "name";
    private static final String COLUOM_DESIGNATION = "designation";
    private static final String COLUOM_AGE = "age";
    private static final String COLUOM_SALARY = "salary";
    private static final String COLUOM_ADDRESS = "address";

    // table name
    private static final String TABLE_NAME = "emp_table";

    private static final String CREATE_TABLE = "Create Table " + TABLE_NAME + " ("
            + COLUOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUOM_NAME + " TEXT NOT NULL,"
            + COLUOM_DESIGNATION + " TEXT NOT NULL,"
            + COLUOM_AGE + " TEXT NOT NULL,"
            + COLUOM_SALARY + " TEXT NOT NULL, "
            + COLUOM_ADDRESS + " TEXT NOT NULL)";

    public DBManager(@Nullable Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public long addEmpRecord(Employee employee) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUOM_NAME, employee.getName());
        values.put(COLUOM_DESIGNATION, employee.getDesignation());
        values.put(COLUOM_AGE, employee.getAge());
        values.put(COLUOM_SALARY, employee.getSalary());
        values.put(COLUOM_ADDRESS, employee.getAddress());


        return db.insert(TABLE_NAME, null, values);

    }

    public List<Employee> getAllEmpRecord() {
        List<Employee> employeeList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * From " + TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String designation = cursor.getString(2);
                String age = cursor.getString(3);
                String salary = cursor.getString(4);
                String address = cursor.getString(5);

                Employee employee = new Employee(id, name, designation, age, salary, address);
                employeeList.add(employee);


            } while (cursor.moveToNext());
        }


        return employeeList;
    }


    public int updateEmpInfo(Employee employee) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUOM_NAME, employee.getName());
        values.put(COLUOM_DESIGNATION, employee.getDesignation());
        values.put(COLUOM_AGE, employee.getAge());
        values.put(COLUOM_SALARY, employee.getSalary());
        values.put(COLUOM_ADDRESS, employee.getAddress());

        return database.update(TABLE_NAME, values, "id=?", new String[]{String.valueOf(employee.getId())});

    }

    public int deleteEmpRecord(int e_id) {
        SQLiteDatabase database = getWritableDatabase();

        return database.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(e_id)});

    }
}
