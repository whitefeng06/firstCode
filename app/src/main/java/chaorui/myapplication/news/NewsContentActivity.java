package chaorui.myapplication.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import chaorui.myapplication.BaseActivity;
import chaorui.myapplication.R;

/**
 * Created by Administrator on 2017/3/24.
 */

public class NewsContentActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_news_content);
        String newsTitle=getIntent().getStringExtra("news_title");
        String newsContent=getIntent().getStringExtra("news_content");
        NewsContentFragment newsContentFragment= (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.news_content_fg);
        newsContentFragment.refresh(newsTitle,newsContent);
    }
    public static void actionStart(Context context,String newsTitle, String newsContent){
        Intent intent=new Intent(context,NewsContentActivity.class);
        intent.putExtra("news_title",newsTitle);
        intent.putExtra("news_content",newsContent);
         context.startActivity(intent);
    }
}
