package chaorui.myapplication.design;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import chaorui.myapplication.BaseActivity;
import chaorui.myapplication.R;

/**
 * Created by Administrator on 2017/3/31.
 */

public class FruitDetailActivity extends BaseActivity {
    public static final String FRUIT_NAME = "fruit_name";
    public static final String FRUIT_IMAGE_ID = "fruit_image_id";
    private ImageView fruit_image_view;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsing_toolbar;
    private TextView fruit_content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_fruit);
        initView();
        Intent intent = getIntent();
        String fruitName = intent.getStringExtra(FRUIT_NAME);
        int fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsing_toolbar.setTitle(fruitName);
        Glide.with(this).load(fruitImageId).into(fruit_image_view);
        fruit_content.setText(generateFruitContent(fruitName));
    }

    private void initView() {
        fruit_image_view = (ImageView) findViewById(R.id.fruit_image_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
      fruit_content=(TextView) findViewById(R.id.fruit_content);
    }
    private String generateFruitContent(String content){
        StringBuilder builder=new StringBuilder();
        for (int i = 0; i <500 ; i++) {
            builder.append(content);
        }
        return builder.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }
}
