package com.polaris.empmgtsystem.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.polaris.empmgtsystem.DBManager.DBManager;
import com.polaris.empmgtsystem.Model.Employee;
import com.polaris.empmgtsystem.R;

import java.util.List;

public class ShowAllRecActivity extends AppCompatActivity {
ListView listView;
List<Employee>list;
ArrayAdapter<Employee>arrayAdapter;
DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_rec);
        listView=findViewById(R.id.listView);
        dbManager=new DBManager(this);

        list=dbManager.getAllEmpRecord();
        arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Employee employee= list.get(position);

                Intent intent=new Intent(ShowAllRecActivity.this,UpdateRecActivity.class);
                intent.putExtra("EMPLOYEE",employee);
                startActivity(intent);

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder=new AlertDialog.Builder(ShowAllRecActivity.this);
                builder.setMessage("Are u sure to delete ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                Employee employee= list.get(position);
                int e_id=employee.getId();
                int result=dbManager.deleteEmpRecord(e_id);

                if (result>0)
                {
                    Toast.makeText(ShowAllRecActivity.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                    list.remove(employee);
                    arrayAdapter.notifyDataSetChanged();
                }
                else {
                    Toast.makeText(ShowAllRecActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                }


                    }
                });

                builder.setNegativeButton("No",null);
                builder.show();

                return true;
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        list=dbManager.getAllEmpRecord();
        arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);
    }
}
