package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView lvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = findViewById(R.id.activity_main_btn_start);
        btnStart.setOnClickListener(this);
        Button btnPause = findViewById(R.id.activity_main_btn_pause);
        btnPause.setOnClickListener(this);
        Button btnStop = findViewById(R.id.activity_main_btn_stop);
        btnStop.setOnClickListener(this);
        Button btnNext = findViewById(R.id.activity_main_btn_next);
        btnNext.setOnClickListener(this);
        Button btnLast = findViewById(R.id.activity_main_btn_last);
        btnLast.setOnClickListener(this);

        lvList = findViewById(R.id.activity_main_lv_musicList);
        String[] list = listToArray(MusicService.getMusicNameList());
//        String[] list = new String[]{"重庆森林","Dream it possible","Stars"};
        System.out.println(list);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        lvList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvList.setAdapter(adapter1);
    }


    public void playMusic(View v){
        Intent intent = new Intent(this,MusicService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("key",MusicService.Control.PLAY);
        intent.putExtras(bundle);
        startService(intent);
    }

    public void pauseMusic(View v){
        Intent intent = new Intent(this,MusicService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("key",MusicService.Control.PAUSE);
        intent.putExtras(bundle);
        startService(intent);
    }

    public void stopMusic(View v){
        Intent intent = new Intent(this,MusicService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("key",MusicService.Control.STOP);
        intent.putExtras(bundle);
        startService(intent);
    }

    public void lastMusic(View v){
        Intent intent = new Intent(this,MusicService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("key",MusicService.Control.LAST);
        intent.putExtras(bundle);
        startService(intent);
    }

    public void nextMusic(View v){
        Intent intent = new Intent(this,MusicService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("key",MusicService.Control.NEXT);
        intent.putExtras(bundle);
        startService(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_main_btn_start:
                playMusic(view);
                break;
            case R.id.activity_main_btn_pause:
                pauseMusic(view);
                break;
            case R.id.activity_main_btn_stop:
                stopMusic(view);
                break;
            case R.id.activity_main_btn_next:
                nextMusic(view);
                break;
            case R.id.activity_main_btn_last:
                lastMusic(view);
                break;
        }
    }

    private String[] listToArray(List<String> list){
        String[] array = new String[list.size()];
        for (int i=0;i<list.size();i++){
            array[i] = list.get(i);
        }
        return array;
    }
}
