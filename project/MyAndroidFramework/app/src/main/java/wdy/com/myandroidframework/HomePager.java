package wdy.com.myandroidframework;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import wdy.com.myandroidframework.domain.GankResultItem;
import wdy.com.myandroidframework.domain.ZhihuLatest;
import wdy.com.myandroidframework.global.Constants;

/**
 * Created by wdy on 16/6/19.
 */
public class HomePager extends BasePager {

    private ListView newList;
    private GankResultItem resultItem;
    private ZhihuLatest zhihuLatest;
    private ImageView mImageView;
    private NewsListAdapter newsListAdapter;
    private View view;

    public HomePager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        // 拿到数据
        getDataFromServer();
        tvTitle.setText("首页");
        if (view == null){
            view = View.inflate(mActivity, R.layout.pager_menu_detail_news,null);
            frameLayout.addView(view);
        }
        newList = (ListView)view.findViewById(R.id.news_list);
    }


    private void getDataFromServer(){
        RequestQueue mQueue = Volley.newRequestQueue(mActivity);

        StringRequest strReq = new StringRequest(Request.Method.GET,
                Constants.ZHIHU_DAILY_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        priseResultData(response);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(mActivity,"失败",Toast.LENGTH_SHORT).show();
                    }
                });
        mQueue.add(strReq);
    }

    protected void priseResultData(String result){
        Gson gson = new Gson();
        zhihuLatest = gson.fromJson(result, ZhihuLatest.class);
        if (newsListAdapter == null){
            newsListAdapter = new NewsListAdapter();
            newList.setAdapter(newsListAdapter);
        }else {
            newsListAdapter.notifyDataSetChanged();
        }
    }

    private class NewsListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return zhihuLatest.stories.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view ;
            if(convertView==null){
                view = View.inflate(mActivity.getApplicationContext(), R.layout.news_item, null);
            }else{
                view = convertView;
            }
            mImageView = (ImageView)view.findViewById(R.id.new_item_iv);
            TextView title = (TextView)view.findViewById(R.id.new_item_title);
            TextView subTitle = (TextView)view.findViewById(R.id.new_item_subtitle);
            ZhihuLatest.ZhihuNewsItem newsItem = zhihuLatest.stories.get(position);

            System.out.print("ga_prefix-----------"+newsItem.ga_prefix + newsItem.title);

            title.setText(newsItem.title);
            subTitle.setText(newsItem.ga_prefix);

            Picasso.with(mActivity)
                    .load(newsItem.images[0])
                    .placeholder(R.drawable.icon_1_d)
                    .error(R.drawable.icon_1_d)
                    .into(mImageView);
            return view;
        }
    }
}
