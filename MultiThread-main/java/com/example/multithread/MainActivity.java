package com.example.multithread;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView showTv;
    Button downloadBtn;
    DownloadAffair downloadAffair;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        public void handleMessage(Message msg){
            if (msg.what==1){
                showTv.setText("下载完成！");
            }else {
                int ratio = (int) msg.obj;
                showTv.setText("loading... "+ ratio +"% finished");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showTv = findViewById(R.id.activity_main_tv_show);
        downloadBtn = findViewById(R.id.activity_main_btn_download);

        downloadBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.activity_main_btn_download:
                downloadAffair = new DownloadAffair();
                downloadAffair.execute(100);
                break;
        }

    }

    // 括号内第一个参数为params类型，即休眠时间，第二个参数为publishProgress()方法参数，即执行进度
    // 第三个参数为doInBackground()方法返回值
    class DownloadAffair extends AsyncTask<Integer,Integer,String> {

        @Override
        protected void onPreExecute() {
            System.out.println("start download!");
            setTitle("正在下载任务...");
            super.onPreExecute();
        }

        //此处的参数类型Integer根据AsyncTask<Integer,Integer,String>中的第一个Integer设置
        @Override
        protected String doInBackground(Integer... params) {
            for (int i=0;i<=100;i++){
                publishProgress(i);
                try {
                    Thread.sleep(params[0]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//            System.out.println("完成下载！");
            return "下载完成！";
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            //这个函数在doInBackground调用publishProgress时触发，虽然调用时只有一个参数
            //但是这里取到的是一个数组,所以要用progesss[0]来取值
            //第n个参数就用progress[n]来取值
            Message msg = Message.obtain();
            msg.obj = progress[0];
            if (progress[0]==100){
                msg.what = 1;
            }else {
                msg.what = 0;
            }
            handler.sendMessage(msg);
//            System.out.println("loading... "+progress[0]+"% finished");
//            showTv.setText("loading... "+progress[0]+"% finished");
            super.onProgressUpdate(progress);
        }

        @Override
        protected void onPostExecute(String result) {
            //doInBackground返回时触发，换句话说，就是doInBackground执行完后触发
            //这里的result就是上面doInBackground执行后的返回值，所以这里是"执行完毕"
            System.out.println("download finished!");
            setTitle(result);
            super.onPostExecute(result);
        }

    }
}


