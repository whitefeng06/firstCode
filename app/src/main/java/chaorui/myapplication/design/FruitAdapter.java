package chaorui.myapplication.design;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import chaorui.myapplication.R;

/**
 * Created by Administrator on 2017/3/31.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> list;
    private Context mcontext;

    public FruitAdapter(List<Fruit> list) {
        this.list = list;
    }

    public void setList(List<Fruit> list) {
        this.list = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView fruit_tv;
        ImageView fruit_img;
        CardView card_view;
        public ViewHolder(View itemView) {
            super(itemView);
            card_view= (CardView) itemView;
            fruit_img= (ImageView) itemView.findViewById(R.id.fruit_img);
            fruit_tv= (TextView) itemView.findViewById(R.id.fruit_tv);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mcontext==null){
            mcontext=parent.getContext();
        }
        View view= LayoutInflater.from(mcontext).inflate(R.layout.item_fruit,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Fruit fruit=list.get(position);
                Intent intent=new Intent(mcontext,FruitDetailActivity.class);
                intent.putExtra(FruitDetailActivity.FRUIT_NAME,fruit.getName());
                intent.putExtra(FruitDetailActivity.FRUIT_IMAGE_ID,fruit.getImageId());
                mcontext.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit=list.get(position);
        holder.fruit_tv.setText(fruit.getName());
        Glide.with(mcontext).load(fruit.getImageId()).into(holder.fruit_img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
