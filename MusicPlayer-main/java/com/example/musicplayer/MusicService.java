package com.example.musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MusicService extends Service {

    private final String TAG = "Music Service";

    private int startId;

    //指向当前播放的音乐
    private int pointer;

    private MediaPlayer mediaPlayer;

    private static Field[] fields;

    private static List list = new ArrayList<String>();

    //用于表示播放器的播放、暂停和停止功能
    public enum Control{
        PLAY, PAUSE, STOP, NEXT, LAST
    }

    public MusicService(){

    }

    public static List<String> getMusicNameList(){
        if (fields==null){
            fields = R.raw.class.getDeclaredFields();
            for (int i=0;i<fields.length;i++){
                list.add(fields[i].getName());
            }
        }
        return list;
    }

    private void getAllMusicResource() {
        fields = R.raw.class.getDeclaredFields();
        int rawId = 0;
        for (int i=0;i<fields.length;i++){
            try {
                rawId = fields[i].getInt(R.raw.class);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            list.add(fields[i].getName());
        }
    }

    //创建服务
    @Override
    public void onCreate() {
        if (fields==null){
            getAllMusicResource();
        }
        //初始化为第一首音乐
        pointer = 0;
        if (mediaPlayer==null){
            try {
                //获取第一首音乐的资源
                mediaPlayer = MediaPlayer.create(this, fields[pointer].getInt(R.raw.class));
                mediaPlayer.setLooping(true);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //记录本次的启动ID
        this.startId = startId;

        //获取从startService()方法中传递的Intent对象中的bundle对象
        Bundle bundle = intent.getExtras();
        if (bundle!=null){
            //绑定数据，存放一个序列化对象，通常为自定义类型
            Control control = (Control) bundle.getSerializable("key");
            if (control!=null){
                 switch (control){
                     case PLAY:
                         play();
                         break;
                     case PAUSE:
                         pause();
                         break;
                     case STOP:
                         stop();
                         break;
                     case NEXT:
                         next();
                         break;
                     case LAST:
                         last();
                         break;
                 }
            }

        }
        return super.onStartCommand(intent, flags, startId);
    }

    //播放音乐
    public void play(){
        if (!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    //下一首
    public void next(){
        if (mediaPlayer!=null){
            //重置mediaPlayer,设置新的音乐
            mediaPlayer.stop();
            mediaPlayer.release();
            pointer = (pointer + 1)%fields.length;
            try {
                mediaPlayer = MediaPlayer.create(this,fields[pointer].getInt(R.raw.class));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
        }
    }

    //上一首
    public void last(){
        if (mediaPlayer!=null){
            //重置mediaPlayer,设置新的音乐
            mediaPlayer.stop();
            mediaPlayer.release();
            pointer = pointer - 1;
            if (pointer<0){
                pointer = pointer + fields.length;
            }
            try {
                mediaPlayer = MediaPlayer.create(this,fields[pointer].getInt(R.raw.class));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
        }
    }

    //暂停播放
    public void pause(){
        if (mediaPlayer!=null&&mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
    }

    //终止播放
    public void stop(){
        if (mediaPlayer!=null){
            mediaPlayer.stop();
        }
        stopSelf(startId);
    }

    //销毁播放服务
    @Override
    public void onDestroy() {
        if (mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not implemented");
    }
}
