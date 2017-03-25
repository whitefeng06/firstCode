package chaorui.myapplication.netstate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import chaorui.myapplication.BaseActivity;
import chaorui.myapplication.R;

/**
 * Created by Administrator on 2017/3/24.
 */

public class NetStateActivity extends BaseActivity {
    private IntentFilter intentFilter;
    private NetworkChangeReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_netstate);
        IntentFilter filter=new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        receiver=new NetworkChangeReceiver();
        registerReceiver(receiver,filter);
    }

    class NetworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
           NetworkInfo info= manager.getActiveNetworkInfo();
            if (info!=null && info.isAvailable()){
                toast(NetStateActivity.this,"网路可用");
            }else{
                toast(NetStateActivity.this,"网路不可用");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
