package chaorui.myapplication.news;

import android.os.Bundle;
import android.support.annotation.Nullable;

import chaorui.myapplication.BaseActivity;
import chaorui.myapplication.R;

/**
 * 碎片实践-新闻适配屏幕
 * Created by Administrator on 2017/3/24.
 */

public class NewsActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_news);

    }
}
