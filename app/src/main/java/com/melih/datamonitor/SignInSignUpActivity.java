package com.melih.datamonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class SignInSignUpActivity extends AppCompatActivity {
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_sign_up);

        username = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
    }

    public void signup(View view){
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://www.melihcakirtas.com/users.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.contains("1")){
                    Intent intent = new Intent(SignInSignUpActivity.this,DataActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(SignInSignUpActivity.this, "Kullanıcı adı veya şifre hatalı", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params =new HashMap<>();
                params.put("username",username.getText().toString());
                params.put("password",password.getText().toString());
                return params;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);
    }
    public void signin(View view){
        StringRequest stringRequestt = new StringRequest(Request.Method.POST, "http://www.melihcakirtas.com/insert.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success =  jsonObject.getString("success");

                    if(success.equals("1")){
                        Toast.makeText(SignInSignUpActivity.this, "Başarıyla Oluşturuldu", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username",username.getText().toString().trim());
                params.put("password",password.getText().toString().trim());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequestt);
    }
}
