package com.example.ra.servicelab;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ImageButton play=(ImageButton)findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,MediaPlayerService.class);
                intent.setAction("play");
                startService(intent);

                NotificationCompat.Builder builder= new NotificationCompat.Builder(MainActivity.this);
                builder.setSmallIcon(R.drawable.ic_action_playback_play);
                builder.setContentTitle("Ra Music");
                builder.setContentText("Play this shit DJ");
                Intent intent2= new Intent(MainActivity.this,MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,(int) System.currentTimeMillis(),intent2,0);
                builder.setContentIntent(pendingIntent);
                builder.setAutoCancel(true);

                Notification notification= builder.build();
                NotificationManager manager= (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                manager.notify(1, notification);
            }
        });
        ImageButton pause=(ImageButton)findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,MediaPlayerService.class);
                intent.setAction("pause");
                startService(intent);
            }
        });

        ImageButton destroy=(ImageButton)findViewById(R.id.destroy);
        destroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,MediaPlayerService.class);
                stopService(intent);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
