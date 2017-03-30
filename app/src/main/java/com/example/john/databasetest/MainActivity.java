package com.example.john.databasetest;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DataBase mydb;
    EditText editText;
    Button button;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DataBase(this);
        editText=(EditText)findViewById(R.id.editText);
        button=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);
        InsertData();
        ViewData();
    }
    public void InsertData(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean InsertedData= mydb.InsertData(editText.getText().toString());
                if(InsertedData=true){
                    Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Data Did Not Inserted",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
    public void ViewData(){
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=mydb.ViewAllData();
                if(res.getCount()==0){
                    ShowMessage("Error","No Content");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()) {
                    buffer.append("ID : " + res.getString(0) + "\n");
                    buffer.append("Name : " + res.getString(1) + "\n\n");
                }
                ShowMessage("Data",buffer.toString());
            }

        }

        );

    }
    public void ShowMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();



    }

}
