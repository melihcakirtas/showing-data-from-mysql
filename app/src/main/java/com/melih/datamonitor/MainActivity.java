package com.melih.datamonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    TextView date,timex ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = findViewById(R.id.textdate);
        timex = findViewById(R.id.texttime);
        DateFormat df = DateFormat.getDateInstance();
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        String gmt_time = df.format(new Date());
        date.setText(gmt_time);


        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+3:00"));
        Date time = calendar.getTime();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3:00"));
        String localtime = dateFormat.format(time);
        timex.setText(localtime);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

              ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //we are connected to a network

                    Intent intent = new Intent(MainActivity.this,DataActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent con = new Intent(MainActivity.this,ConnectionlessActivity.class);
                    startActivity(con);
                }
            }
        },3000);

    }

}


