package com.example.newdb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button insert,view ,delete,update;
    EditText id,name,post,salary;
    DB_Helper DB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id=findViewById(R.id.editTextTextPersonName);
        name=findViewById(R.id.editTextTextPersonName2);
        post=findViewById(R.id.editTextTextPersonName3);
        salary=findViewById(R.id.editTextTextPersonName4);

        insert=findViewById(R.id.b1);
        view=findViewById(R.id.b2);
        delete=findViewById(R.id.b3);
        update=findViewById(R.id.b4);

        DB=new DB_Helper(MainActivity.this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String idtxt=id.getText().toString();
                String nametxt=name.getText().toString();
                String posttxt=post.getText().toString();
                String salarytxt=salary.getText().toString();

                Boolean check=DB._insertdata(idtxt,nametxt,posttxt,salarytxt);
                if (check==true)
                {
                    Toast.makeText(MainActivity.this,"Empolyee Registered",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this,"Empolyee Not Registered",Toast.LENGTH_SHORT).show();
                }
                id.setText("");
                name.setText("");
                post.setText("");
                salary.setText("");

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cr= DB.getdata();
                if (cr.getCount()==0){
                    Toast.makeText(MainActivity.this,"No Data Exists",Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer sb=new StringBuffer();
                while(cr.moveToNext()){
                    sb.append("ID:"+cr.getString(0)+"\n");
                    sb.append("Name:"+cr.getString(1)+"\n");
                    sb.append("Post:"+cr.getString(2)+"\n");
                    sb.append("Salary:"+cr.getString(3)+"\n\n");
                }

                AlertDialog.Builder ab=new AlertDialog.Builder(MainActivity.this);
                ab.setCancelable(true);
                ab.setTitle("Empoyees");
                ab.setMessage(sb.toString());
                ab.show();

            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idtxt = id.getText().toString();
                Boolean checkudeletedata = DB.deletedata(idtxt);
                if(checkudeletedata==true)
                    Toast.makeText(MainActivity.this, "Employee Removed", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Employee Not Removed", Toast.LENGTH_SHORT).show();
            }

        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idtxt     =id.getText().toString();
                String nametxt   = name.getText().toString();
                String posttxt   = post.getText().toString();
                String salarytxt = salary.getText().toString();

                Boolean checkupdatedata = DB.updatedata(idtxt,nametxt,posttxt,salarytxt);
                if(checkupdatedata==true)
                    Toast.makeText(MainActivity.this, "Information Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Information Not Updated", Toast.LENGTH_SHORT).show();
            }
        });


    }
}