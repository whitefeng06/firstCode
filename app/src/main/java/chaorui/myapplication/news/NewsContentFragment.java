package chaorui.myapplication.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import chaorui.myapplication.R;


/**
 * Created by Administrator on 2017/3/24.
 */

public class NewsContentFragment extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fg_news_content,container,false);
        return view;
    }
    public void refresh(String title,String content){
        View visibilityLayout=view.findViewById(R.id.visibility_layout);
        visibilityLayout.setVisibility(View.VISIBLE);
        TextView newsTitleText =(TextView) view.findViewById(R.id.news_title);
        TextView newsContentText=(TextView) view.findViewById(R.id.news_content);
        newsTitleText.setText(title);
        newsContentText.setText(content);

    }
}

