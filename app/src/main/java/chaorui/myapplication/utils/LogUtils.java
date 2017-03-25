package chaorui.myapplication.utils;


import android.util.Log;

/**
 * Created by Administrator on 2017/3/23.
 */

public class LogUtils {
    public static boolean LogTag=true;
    public static void i(String key,String value){
        if (LogTag){
            Log.i(key,value);
        }
    }
    public static void v(String key,String value){
        if (LogTag){
            Log.v(key,value);
        }
    }
    public static void d(String key,String value){
        if (LogTag){
            Log.d(key,value);
        }
    }
    public static void w(String key,String value){
        if (LogTag){
            Log.w(key,value);
        }
    }
    public static void e(String key,String value){
        if (LogTag){
            Log.e(key,value);
        }
    }
}
