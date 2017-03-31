package chaorui.myapplication.media;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import chaorui.myapplication.BaseActivity;
import chaorui.myapplication.R;
import chaorui.myapplication.news.NewsActivity;

/**
 * Created by Administrator on 2017/3/29.
 */

public class MediaActivity extends BaseActivity implements View.OnClickListener {
    private Button nof_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_media);
        initView();
    }

    private void initView() {
        nof_btn = (Button) findViewById(R.id.nof_btn);

        nof_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nof_btn:
                NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Intent intent=new Intent(MediaActivity.this, NewsActivity.class);
                PendingIntent pit=PendingIntent.getActivity(this,0,intent,0);
                Notification notification=new NotificationCompat.Builder(this)
                        .setContentTitle("This is a content title")
                        .setContentText("Learn how to build notifications,send and sync data, and use voice actions.Get the official Android IDE and developer tools to build apps for Android.")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to build notifications,send and sync data, and use voice actions.Get the official Android IDE and developer tools to build apps for Android."))
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                        .setTicker("通知来了")
                        .setContentIntent(pit)
                        .setAutoCancel(true)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .build();
                manager.notify(1,notification);
                break;
        }
    }
}
