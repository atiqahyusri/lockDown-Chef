package com.example.androidlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {

    private static final String TAG = UpdateActivity.class.getSimpleName();
    private static final String URL_UPDATE = "http://192.168.1.7/android_login/Update.php";
    ProgressDialog progressDialog;
    String finalResult ;
    HashMap<String,String> hashMap = new HashMap<>();
    EditText Name, Email, Password;
    Button Update;
    String IdHolder, NameHolder, EmailHolder, PasswordHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        Name = (EditText)findViewById(R.id.editName);
        Email = (EditText)findViewById(R.id.editEmail);
        Password = (EditText)findViewById(R.id.editPassword);

        Update = (Button)findViewById(R.id.Update);

        // Receive Student ID, Name , Phone Number , Class Send by previous ShowSingleRecordActivity.
        IdHolder = getIntent().getStringExtra("unique_id");
        NameHolder = getIntent().getStringExtra("name");
        EmailHolder = getIntent().getStringExtra("email");
        PasswordHolder = getIntent().getStringExtra("password");

        // Setting Received Student Name, Phone Number, Class into EditText.
        Name.setText(NameHolder);
        Email.setText(EmailHolder);
        Password.setText(PasswordHolder);

        // Adding click listener to update button .
        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Getting data from EditText after button click.
                GetDataFromEditText();

                // Sending Student Name, Phone Number, Class to method to update on server.
                RecordUpdate(IdHolder,NameHolder,EmailHolder, PasswordHolder);

            }
        });


    }

    // Method to get existing data from EditText.
    public void GetDataFromEditText(){

        NameHolder = Name.getText().toString();
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();

    }

    // Method to Update Student Record.
    private void RecordUpdate(final String id, final String name, final String email, final String password){

        String tag_string_req = "req_update";
        progressDialog.setMessage("Updating ...");
        showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_UPDATE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Update Response: " + response.toString());
                hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        Toast.makeText(getApplicationContext(),
                                "User successfully updated", Toast.LENGTH_LONG).show();
                        // Launch login activity
                        Intent intent = new Intent(UpdateActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Update Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("unique_id",id);
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        // Adding request to request queue
        MyApplication.getInstance().addToRequestQueue(strReq, tag_string_req);
        }
    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }
    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}

