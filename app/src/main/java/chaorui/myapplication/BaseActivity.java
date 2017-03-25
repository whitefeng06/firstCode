package chaorui.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import chaorui.myapplication.utils.LogUtils;

/**
 * Created by Administrator on 2017/3/23.
 */

public class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i("className",getClass().getSimpleName());
    }

    public void toast(Context context,String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();

    }
}
