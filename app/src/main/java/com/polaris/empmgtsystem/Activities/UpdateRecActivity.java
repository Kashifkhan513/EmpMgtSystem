package com.polaris.empmgtsystem.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.polaris.empmgtsystem.DBManager.DBManager;
import com.polaris.empmgtsystem.Model.Employee;
import com.polaris.empmgtsystem.R;

public class UpdateRecActivity extends AppCompatActivity {
    EditText edit_Name;
    EditText edit_Designation;
    EditText edit_Age;
    EditText edit_Salary;
    EditText edit_Address;
    int id;
    DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_rec);
        initViews();
        dbManager=new DBManager(this);
      Employee employee= (Employee) getIntent().getExtras().getSerializable("EMPLOYEE");
       id=employee.getId();
      edit_Name.setText(employee.getName());
      edit_Designation.setText(employee.getDesignation());
      edit_Age.setText(employee.getAge());
      edit_Salary.setText(employee.getSalary());
      edit_Address.setText(employee.getAddress());


    }


    public void btnUpdate(View view) {

        String name=edit_Name.getText().toString();
        String designation=edit_Designation.getText().toString();
        String age=edit_Age.getText().toString();
        String salary=edit_Salary.getText().toString();
        String address=edit_Address.getText().toString();

        Employee employee=new Employee(id,name,designation,age,salary,address);
      int result=dbManager.updateEmpInfo(employee);

      if (result>0)
      {
          Toast.makeText(this, "Record Updated Successfully", Toast.LENGTH_SHORT).show();
      }
      else {
          Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT).show();
      }
    }

    private void initViews() {

        edit_Name = findViewById(R.id.editName);
        edit_Designation = findViewById(R.id.editDesignation);
        edit_Age = findViewById(R.id.editAge);
        edit_Salary = findViewById(R.id.editSalary);
        edit_Address = findViewById(R.id.editAddress);
    }


}
