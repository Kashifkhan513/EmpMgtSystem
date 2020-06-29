package com.polaris.empmgtsystem.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.polaris.empmgtsystem.DBManager.DBManager;
import com.polaris.empmgtsystem.Model.Employee;
import com.polaris.empmgtsystem.R;

public class AddEmpActivity extends AppCompatActivity {
    EditText edit_Name;
    EditText edit_Designation;
    EditText edit_Age;
    EditText edit_Salary;
    EditText edit_Address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emp);
        initViews();


    }


    private void initViews() {

        edit_Name = findViewById(R.id.editName);
        edit_Designation = findViewById(R.id.editDesignation);
        edit_Age = findViewById(R.id.editAge);
        edit_Salary = findViewById(R.id.editSalary);
        edit_Address = findViewById(R.id.editAddress);
    }


    public void btnAddRecord(View view) {
        String empName = edit_Name.getText().toString().trim();
        String empDesignation = edit_Designation.getText().toString().trim();
        String empAge = edit_Age.getText().toString().trim();
        String empSalary = edit_Salary.getText().toString().trim();
        String empAddress = edit_Address.getText().toString().trim();


        if (empName.isEmpty()) {
            edit_Name.setError("Enter Name...");
            return;
        }
        if (empDesignation.isEmpty()) {
            edit_Designation.setError("Enter Designation...");
            return;
        }
        if (empAge.isEmpty()) {
            edit_Age.setError("Enter Age...");
            return;
        }
        if (empSalary.isEmpty()) {
            edit_Salary.setError("Enter Salary...");
            return;
        }

        if (empAddress.isEmpty()) {
            edit_Address.setError("Enter Address...");
            return;
        }


        DBManager dbManager = new DBManager(this);
        Employee employee = new Employee(empName, empDesignation, empAge, empSalary, empAddress);

        long result = dbManager.addEmpRecord(employee);

        if (result != -1) {
            Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Something Wrong", Toast.LENGTH_SHORT).show();
        }


    }


    public void btnShowAllRecord(View view) {

        startActivity(new Intent(AddEmpActivity.this, ShowAllRecActivity.class));
    }
}
