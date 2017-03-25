package chaorui.myapplication.recylerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import chaorui.myapplication.BaseActivity;
import chaorui.myapplication.R;

/**
 * Created by Administrator on 2017/3/23.
 */

public class RecylerViewActivity extends BaseActivity {
    private RecyclerView rec_view;
    private List<String> list;
    private RecylerViewAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_recyclerview);
        initData();
        initView();
        adapter=new RecylerViewAdapter(list);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        //横向滚动,默认纵向滚动
       // layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //瀑布流
        StaggeredGridLayoutManager sgM=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        rec_view.setLayoutManager(sgM);
        rec_view.setAdapter(adapter);
    }
    private void initView() {
        rec_view = (RecyclerView) findViewById(R.id.rec_view);
    }

    public void initData() {
        list=new ArrayList<String>();
//        list.add("java");
//        list.add("php");
//        list.add("javaScript");
//        list.add("adroid");
//        list.add("html5");
//        list.add("css3");
        list.add(getRandomLength("java"));
        list.add(getRandomLength("php"));
        list.add(getRandomLength("javaScript"));
        list.add(getRandomLength("adroid"));
        list.add(getRandomLength("html5"));
        list.add(getRandomLength("css3"));
        list.add(getRandomLength("ios"));
        list.add(getRandomLength("kotlin"));
    }

    private String getRandomLength(String name){
        Random random=new Random();
        int length=random.nextInt(20)+1;
        StringBuilder builder=new StringBuilder();
        for (int i = 0; i <length ; i++) {
            builder.append(name);
        }
        return builder.toString();
    }
}
