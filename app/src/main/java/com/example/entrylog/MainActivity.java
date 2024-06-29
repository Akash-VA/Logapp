package com.example.entrylog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
EditText t1,t2;
AppCompatButton b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        SharedPreferences pref=getSharedPreferences("Entrylog",MODE_PRIVATE);
        String username=pref.getString("user",null);
        if (username!=null)
        {
            Intent i=new Intent(getApplicationContext(), Login.class);
            startActivity(i);
        }

        t1=(EditText) findViewById(R.id.unametxt);
        t2=(EditText) findViewById(R.id.passtxt);
        b1=(AppCompatButton) findViewById(R.id.loginbtn);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getUsername,getPassword;
                getUsername=t1.getText().toString();
                getPassword=t2.getText().toString();
                if (getUsername.equals("Akash")&&getPassword.equals("qaz@1234"))
                {
                    SharedPreferences pref=getSharedPreferences("Entrylog",MODE_PRIVATE);
                    SharedPreferences.Editor edit=pref.edit();
                    edit.putString("user","Akash");
                    edit.apply();
                    Intent i=new Intent(getApplicationContext(), Login.class);
                    startActivity(i);
                }
                else
                {
                Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}