package chaorui.myapplication.LitePal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

import chaorui.myapplication.BaseActivity;
import chaorui.myapplication.R;

public class LitePalActivity extends BaseActivity implements View.OnClickListener {
    private Button data_create_btn;
    private List<BikeBean> list;
    private EditText num_et;
    private EditText pw_et;
    private Button add_btn;
    private Button query_btn;
    private RecyclerView bike_rcv;
    private BikeAdapter adapter;
    private Button clear_btn;
    private EditText key_et;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_litepal);
        list = new ArrayList<>();
        initView();
        getData("");
        LinearLayoutManager manager = new LinearLayoutManager(this);
        bike_rcv.setLayoutManager(manager);
        adapter = new BikeAdapter(list);
        bike_rcv.setAdapter(adapter);
    }

    private void initView() {
        data_create_btn = (Button) findViewById(R.id.data_create_btn);
        data_create_btn.setOnClickListener(this);
        num_et = (EditText) findViewById(R.id.num_et);
        num_et.setOnClickListener(this);
        pw_et = (EditText) findViewById(R.id.pw_et);
        pw_et.setOnClickListener(this);
        add_btn = (Button) findViewById(R.id.add_btn);
        add_btn.setOnClickListener(this);
        query_btn = (Button) findViewById(R.id.query_btn);
        query_btn.setOnClickListener(this);
        bike_rcv = (RecyclerView) findViewById(R.id.bike_rcv);
        clear_btn = (Button) findViewById(R.id.clear_btn);
        clear_btn.setOnClickListener(this);
        key_et = (EditText) findViewById(R.id.key_et);
        key_et.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.data_create_btn:
                Connector.getDatabase();
                break;
            case R.id.add_btn:
                String net = num_et.getText().toString().trim();
                if (TextUtils.isEmpty(net) || net.contains(" ")) {
                    Toast.makeText(this, "车牌号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                String pet = pw_et.getText().toString().trim();
                if (TextUtils.isEmpty(pet) || pet.contains(" ")) {
                    Toast.makeText(this, "车锁密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                BikeBean bean = new BikeBean();
                bean.setBike_num(num_et.getText().toString());
                bean.setBike_pw(pw_et.getText().toString());
                bean.save();
                if (bean.isSaved()) {
                    toast(LitePalActivity.this, "添加成功");
                    num_et.setText("");
                    pw_et.setText("");
                    getData("");
                    adapter.setBlist(list);
                    adapter.notifyDataSetChanged();
                }
                break;
            case R.id.query_btn:
                getData(key_et.getText().toString());
                adapter.setBlist(list);
                adapter.notifyDataSetChanged();
                break;
            case R.id.clear_btn:
                if (list.size() > 0) {
                    DataSupport.deleteAll(BikeBean.class);
                }
                getData("");
                adapter.setBlist(list);
                adapter.notifyDataSetChanged();
                break;
        }
    }

    public void getData(String key) {
        if (TextUtils.isEmpty(key)) {
            list = DataSupport.findAll(BikeBean.class);
        } else {
            list = DataSupport.where("bike_num=?",key).find(BikeBean.class);
        }
    }

    class BikeAdapter extends RecyclerView.Adapter<BikeAdapter.ViewHolder> {
        List<BikeBean> blist;

        public BikeAdapter(List<BikeBean> bbean) {
            blist = bbean;
        }

        public void setBlist(List<BikeBean> blist) {
            this.blist = blist;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView num_tv, pw_tv;

            public ViewHolder(View itemView) {
                super(itemView);
                num_tv = (TextView) itemView.findViewById(R.id.num_tv);
                pw_tv = (TextView) itemView.findViewById(R.id.pw_tv);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_litepal, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataSupport.delete(BikeBean.class,holder.getAdapterPosition());
                    getData("");
                    blist=list;
                    notifyDataSetChanged();
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.pw_tv.setText(blist.get(position).getBike_pw());
            holder.num_tv.setText(blist.get(position).getBike_num());
        }

        @Override
        public int getItemCount() {
            //            if (blist!=null&&blist.size()>0) {
            //                return blist.size();
            //            }else{
            //                return 0;
            //            }
            return blist.size();
        }
    }
}
