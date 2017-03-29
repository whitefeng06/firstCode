package chaorui.myapplication.LitePal;

import org.litepal.crud.DataSupport;


public class BikeBean extends DataSupport {
    private String bike_num;
    private String bike_pw;

    public String getBike_num() {
        return bike_num;
    }

    public void setBike_num(String bike_num) {
        this.bike_num = bike_num;
    }

    public String getBike_pw() {
        return bike_pw;
    }

    public void setBike_pw(String bike_pw) {
        this.bike_pw = bike_pw;
    }
}
