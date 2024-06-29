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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
EditText t1,t2,t3,t4;
AppCompatButton btl,b1;
String apiurl="http://10.0.4.16:3000/api/students";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        t1=(EditText) findViewById(R.id.nametxt);
        t2=(EditText) findViewById(R.id.adnotxt);
        t3=(EditText) findViewById(R.id.sysnotxt);
        t4=(EditText) findViewById(R.id.depttxt);
        btl=(AppCompatButton) findViewById(R.id.btl);
        b1=(AppCompatButton) findViewById(R.id.addbtn);
        btl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref=getSharedPreferences("Entrylog",MODE_PRIVATE);
                SharedPreferences.Editor edit= pref.edit();
                edit.clear();
                edit.apply();
                Intent i=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getName, getAdmission, getSystem, getDepartment;
                getName = t1.getText().toString();
                getAdmission = t2.getText().toString();
                getSystem = t3.getText().toString();
                getDepartment = t4.getText().toString();
                JSONObject student = new JSONObject();

                try {
                    student.put("name", getName);
                    student.put("name", getAdmission);
                    student.put("name", getSystem);
                    student.put("name", getDepartment);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                //JsonobjectRequest
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, apiurl, student,
                        new Response.Listener() {
                            @Override
                            public void onResponse(Object response) {
                                Toast.makeText(getApplicationContext(), "AddedSucessfully", Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "Something went Wrong!", Toast.LENGTH_LONG).show();
                            }

                        }
                );

                //Request queue
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);

            }
        });

    }
}

