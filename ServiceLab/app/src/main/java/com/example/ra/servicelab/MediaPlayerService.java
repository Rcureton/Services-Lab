package com.example.ra.servicelab;


import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Ra on 3/2/16.
 */
public class MediaPlayerService extends Service {
    MediaPlayer mPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer= new MediaPlayer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();

        if (action.equals("play")){
           onPlay();
        }else if (action.equals("pause")){
            onPause();

        }else{

        }return super.onStartCommand(intent, flags, startId);
    }

    private void onPlay(){
        try {
            String url = "http://download.lisztonian.com/music/download/Clair+de+Lune-113.mp3";
            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mPlayer.setDataSource(url);
            mPlayer.prepareAsync();
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mPlayer.start();
                }
            });
        } catch (Throwable thr){
        }
    }

    private void onPause(){
            mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mPlayer.pause();
                }
            });
        }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlayer.stop();
        mPlayer.release();

    }
}
