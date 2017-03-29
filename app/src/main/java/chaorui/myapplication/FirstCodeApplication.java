package chaorui.myapplication;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;


public class FirstCodeApplication extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
