package chaorui.myapplication.design;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import chaorui.myapplication.BaseActivity;
import chaorui.myapplication.R;

/**
 * Created by Administrator on 2017/3/31.
 */

public class DesignActivity extends BaseActivity {
    private Toolbar toolbar;
    private DrawerLayout drawer_layout;
    private NavigationView nav_view;
    private FloatingActionButton fab;
    private RecyclerView recycler_view;
    private List<Fruit> list = new ArrayList<Fruit>();
    private FruitAdapter adapter;
    private Fruit[] fruits = {new Fruit("Apple", R.drawable.img_apple), new Fruit("Banana", R.drawable.img_banana), new Fruit("Orange", R.drawable.img_orange), new Fruit("Wagermalon", R.drawable.img_watermelon), new Fruit("pear", R.drawable.img_pear)
            , new Fruit("Grape", R.drawable.img_grape), new Fruit("PineApple", R.drawable.img_pineapple), new Fruit("Strawberry", R.drawable.img_strawberry), new Fruit("Cherry", R.drawable.img_cherry), new Fruit("Mango", R.drawable.img_mango)};
    private SwipeRefreshLayout swipe_refresh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_design);
        initView();
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setHomeAsUpIndicator(R.drawable.icon_menu);
        }
        initFruit();
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recycler_view.setLayoutManager(manager);
        adapter = new FruitAdapter(list);
        recycler_view.setAdapter(adapter);

    }

    private void initFruit() {
        list.clear();
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            list.add(fruits[index]);
        }
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        nav_view = (NavigationView) findViewById(R.id.nav_view);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        nav_view.setCheckedItem(R.id.nav_call);
        swipe_refresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipe_refresh.setColorSchemeResources(R.color.colorPrimary);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruit();
            }
        });
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawer_layout.closeDrawers();
                return true;
            }
        });
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Data deleted", Snackbar.LENGTH_SHORT).setAction("否", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toast(DesignActivity.this, "Data restored");
                    }
                }).show();
            }
        });


    }

    /*
     * 刷新列表
     * */
    private void refreshFruit() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruit();
                        adapter.notifyDataSetChanged();
                        swipe_refresh.setRefreshing(false);
                    }
                });

            }
        }).start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer_layout.openDrawer(GravityCompat.START);
                break;
            case R.id.delete:
                toast(this, "你点击了删除");
                break;
            case R.id.backup:
                toast(this, "你点击了返回");
                break;
            case R.id.setting:
                toast(this, "你点击了设置");
                break;
            default:
                break;
        }
        return true;
    }

}
