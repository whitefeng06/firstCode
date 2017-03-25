package chaorui.myapplication.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import chaorui.myapplication.R;

/**
 * Created by Administrator on 2017/3/24.
 */

public class NewsTitleFragment extends Fragment {
    private View view;
    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fg_news_title,container,false);
        RecyclerView rv= (RecyclerView) view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        NewsAdapter adapter=new NewsAdapter(getList());
        rv.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_fg)!=null){
            isTwoPane=true;//可以找到news_content_fg,为双叶模式
        }else{
            isTwoPane=false;//不能找到news_content_fg,为单页模式
        }
    }
    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
        private List<NewsBean> list;

        public NewsAdapter(List<NewsBean> list) {
            this.list = list;
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView newsTitleText;
            public ViewHolder(View itemView) {
                super(itemView);
                newsTitleText= (TextView) itemView.findViewById(R.id.news_title);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news,parent,false);
            final ViewHolder holder=new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NewsBean bean=list.get(holder.getAdapterPosition());
                    if (isTwoPane){
                        //如果是双页模式，就刷新NewsContentFragment内容
                        NewsContentFragment newsContentFragment= (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fg);
                        newsContentFragment.refresh(bean.getTitle(),bean.getContent());
                    }else{
                        //如果是单页模式 ，则直接启动NesContentActivity
                        NewsContentActivity.actionStart(getActivity(),bean.getTitle(),bean.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.newsTitleText.setText(list.get(position).getTitle());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    /**
     *
     * @return 新闻列表
     */
    public List<NewsBean> getList(){
        List<NewsBean> newsList=new ArrayList<NewsBean>();
       for (int i = 0; i <=50 ; i++) {
            NewsBean bean=new NewsBean();
           bean.setTitle("This is news title"+i);
           bean.setContent(getRandomLengthContent("This is news content"+i));
           newsList.add(bean);
        }
        return  newsList;
    }

    /**
     * 随机长度
     * @param s
     * @return
     */
    private String getRandomLengthContent(String s) {
        Random random=new Random();
        int length=random.nextInt(20)+1;
        StringBuilder builder=new StringBuilder();
        for (int i = 0; i <length ; i++) {
            builder.append(s);
        }
        return  builder.toString();
    }
}
