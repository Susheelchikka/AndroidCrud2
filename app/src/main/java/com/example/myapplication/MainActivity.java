package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText id , name , address, deleteId;
    Button addBtn ,delete,update, viewData;
    dbHelper db ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = findViewById(R.id.editTextTextId);
        name = findViewById(R.id.editTextTextName);
        address = findViewById(R.id.editTextTextAddress);
        addBtn = findViewById(R.id.btnAdd);
        viewData = findViewById(R.id.button);
        delete = findViewById(R.id.btnDelete);
        update = findViewById(R.id.btnUpdate);
        deleteId = findViewById(R.id.editTextTextDeleteId);
        db = new dbHelper(this);

        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c = db.getDate();
                if(c.getCount() == 0)
                {
                    Toast.makeText(MainActivity.this,"No Records Found",Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(c.moveToNext())
                {
                    buffer.append("ID : "+c.getString(0)+"\n");
                    buffer.append("NAME : "+c.getString(1)+"\n");
                    buffer.append("ADDRESS : "+c.getString(2)+"\n\n");
                }
                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setCancelable(true);
                b.setTitle("DATA");
                b.setMessage(buffer.toString());
                b.show();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idX = id.getText().toString();
                String nameX = name.getText().toString();
                String addressX = address.getText().toString();

                Boolean checkAdd = db.insertUserData(idX,nameX,addressX);
                if(checkAdd == true)
                {
                    Toast.makeText(MainActivity.this,"ENTRY ADDED",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"ENTRY NOT ADDED",Toast.LENGTH_LONG).show();
                }

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idX = deleteId.getText().toString();
                Boolean checkDelete = db.deleteUserData(idX,null,null);
                if(checkDelete == true)
                {
                    Toast.makeText(MainActivity.this,"ENTRY DELETED",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"ENTRY NOT DELETED",Toast.LENGTH_LONG).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idX = id.getText().toString();
                String nameX = name.getText().toString();
                String addressX = address.getText().toString();

                Boolean checkAdd = db.updateUserData(idX,nameX,addressX);
                if(checkAdd == true)
                {
                    Toast.makeText(MainActivity.this,"ENTRY UPDATES",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"ENTRY NOT UPDATES",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}