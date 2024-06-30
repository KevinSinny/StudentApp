package com.example.studentapp;

import android.content.Intent;
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

public class AddStudent extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    AppCompatButton b1,b2;
    String apiurl="https://courseapplogix.onrender.com/addstudents";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);
        e1=(EditText) findViewById(R.id.firstid);
        e2=(EditText) findViewById(R.id.lastid);
        e3=(EditText) findViewById(R.id.collegeid);
        e4=(EditText) findViewById(R.id.dateid);
        e5=(EditText) findViewById(R.id.courseid);
        e6=(EditText) findViewById(R.id.mobileid);
        e7=(EditText) findViewById(R.id.emailid);
        e8=(EditText) findViewById(R.id.addressid);
        b1=(AppCompatButton) findViewById(R.id.addsbtn);
        b2=(AppCompatButton) findViewById(R.id.backbtn);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname=e1.getText().toString();
                String lastname=e2.getText().toString();
                String college=e3.getText().toString();
                String dob=e4.getText().toString();
                String course=e5.getText().toString();
                String mobile=e6.getText().toString();
                String email=e7.getText().toString();
                String address=e8.getText().toString();

                JSONObject student=new JSONObject();
                try {
                    student.put("firstname",firstname);
                    student.put("lastname",lastname);
                    student.put("college",college);
                    student.put("dob",dob);
                    student.put("course",course);
                    student.put("mobile",mobile);
                    student.put("emai",email);
                    student.put("address",address);
                }
                catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST, apiurl, student,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),"Something went Wrong",Toast.LENGTH_LONG).show();
                            }
                        }
                );
                //Request Queue
                RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(jsonObjectRequest);
            }
        });

    }
}