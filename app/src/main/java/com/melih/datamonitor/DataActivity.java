package com.melih.datamonitor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DataActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    String id,id2,id3;
    String first1,first2,first3;
    String second1,second2,second3;
    String third1,third2,third3;
    String fourth,fourth2,fourth3;
    String fifth1,fifth2,fifth3;
    String date1,date2,date3;
    TextView txtid1,txtf1,txts1,txtt1,txtfourht1,txtfifth1,txtdate1;
    TextView txtid2,txtf2,txts2,txtt2,txtfourht2,txtfifth2,txtdate2;
    TextView txtid3,txtf3,txts3,txtt3,txtfourht3,txtfifth3,txtdate3;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        txtid1 = findViewById(R.id.textid1);
        txtf1 = findViewById(R.id.textfirst1);
        txts1 = findViewById(R.id.textsecond1);
        txtt1 = findViewById(R.id.textthird1);
        txtfourht1 = findViewById(R.id.textfourth1);
        txtfifth1 = findViewById(R.id.textfifth1);
        txtdate1 = findViewById(R.id.textdate1);

        txtid2 = findViewById(R.id.textid2);
        txtf2 = findViewById(R.id.textfirst2);
        txts2 = findViewById(R.id.textsecond2);
        txtt2 = findViewById(R.id.textthird2);
        txtfourht2 = findViewById(R.id.textfourth2);
        txtfifth2 = findViewById(R.id.textfifth2);
        txtdate2 = findViewById(R.id.textdate2);

        txtid3 = findViewById(R.id.textid3);
        txtf3 = findViewById(R.id.textfirst3);
        txts3 = findViewById(R.id.textsecond3);
        txtt3 = findViewById(R.id.textthird3);
        txtfourht3 = findViewById(R.id.textfourth3);
        txtfifth3 = findViewById(R.id.textfifth3);
        txtdate3 = findViewById(R.id.textdate3);

        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigation_view);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.draweropen,R.string.drawerclose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.cıkıs:
                        Intent intent = new Intent(DataActivity.this,SignInSignUpActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.settings:
                        Toast.makeText(DataActivity.this, "ayarlar", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.contactus:
                        AlertDialog.Builder builder = new AlertDialog.Builder(DataActivity.this);
                        builder.setTitle("Bize Ulaşın");
                        builder.setMessage("Mail:melihcakirtas@hotmail.com " + "\n" + "Tel:0530 501 1646" );
                        builder.setIcon(R.drawable.ic_contacts_black_24dp);
                        builder.setCancelable(true);
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        break;
                }
                return true;
            }
        });
    }
    public void getdata(View view){

        String url = "http://www.melihcakirtas.com/index1.php";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).method("GET",null).get().build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
               e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();
                System.out.println(data);
                DataActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try{

                            JSONArray jsonArray1 = new JSONArray(data);
                            for(int i = 0; i<1; i++){
                                JSONObject jsonObject1 = (JSONObject) jsonArray1.get(i);


                                id =  jsonObject1.getString("id");
                                first1 =  jsonObject1.getString("firstdata");
                                second1 =  jsonObject1.getString("seconddata");
                                third1 =  jsonObject1.getString("thirddata");
                                fourth =  jsonObject1.getString("fourthdata");
                                fifth1 = jsonObject1.getString("fifthdata");
                                date1 =    jsonObject1.getString("date");

                                txtid1.setText("id:" + id);
                                txtf1.setText("first data:" + first1);
                                txts1.setText("second data:" + second1);
                                txtt1.setText("third data:" + third1);
                                txtfourht1.setText("fourth data:" + fourth);
                                txtfifth1.setText("fifth data:" + fifth1);
                                txtdate1.setText( date1);
                            }
                            for(int a = 1;a<2;a++){
                                JSONObject jsonObject2 = (JSONObject) jsonArray1.get(a);
                                id2 =  jsonObject2.getString("id");
                                first2 =  jsonObject2.getString("firstdata");
                                second2 =  jsonObject2.getString("seconddata");
                                third2 =  jsonObject2.getString("thirddata");
                                fourth2 =  jsonObject2.getString("fourthdata");
                                fifth2 = jsonObject2.getString("fifthdata");
                                date2 =    jsonObject2.getString("date");

                                txtid2.setText("id:" + id2);
                                txtf2.setText("first data:" + first2);
                                txts2.setText("second data:" + second2);
                                txtt2.setText("third data:" + third2);
                                txtfourht2.setText("fourth data:" + fourth2);
                                txtfifth2.setText("fifth data:" + fifth2);
                                txtdate2.setText( date2);

                            }

                            for(int b = 2;b<3;b++){
                                JSONObject jsonObject3 = (JSONObject) jsonArray1.get(b);
                                id3 =  jsonObject3.getString("id");
                                first3 =  jsonObject3.getString("firstdata");
                                second3 =  jsonObject3.getString("seconddata");
                                third3 =  jsonObject3.getString("thirddata");
                                fourth3 =  jsonObject3.getString("fourthdata");
                                fifth3 = jsonObject3.getString("fifthdata");
                                date3 =    jsonObject3.getString("date");

                                txtid3.setText("id:" + id3);
                                txtf3.setText("first data:" + first3);
                                txts3.setText("second data:" + second3);
                                txtt3.setText("third data:" + third3);
                                txtfourht3.setText("fourth data:" + fourth3);
                                txtfifth3.setText("fifth data:" + fifth3);
                                txtdate3.setText( date3);

                            }

                            System.out.println(id + id2 + id3);
                        } catch (Exception e){
                                e.printStackTrace();
                        }
                    }
                });

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
