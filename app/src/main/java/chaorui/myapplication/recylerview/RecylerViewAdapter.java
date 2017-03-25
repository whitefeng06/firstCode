package chaorui.myapplication.recylerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import chaorui.myapplication.R;

/**
 * Created by Administrator on 2017/3/23.
 */

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.ViewHolder>{
    public List<String> list;
    public RecylerViewAdapter(List<String> list) {
        this.list=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recv_list_hor,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.codeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                Toast.makeText(v.getContext(),list.get(position),Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static  class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        ImageView iv;
        View codeView;

        public ViewHolder(View itemView) {
            super(itemView);
            codeView=itemView;
            iv= (ImageView) itemView.findViewById(R.id.iv);
            tv= (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
