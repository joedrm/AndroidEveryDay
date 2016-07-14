package wdy.com.baseadapterandlistviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private ArrayList<ArticleItem> dataList = new ArrayList<ArticleItem>();
    private ListView lv;
    private MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataList.add(new ArticleItem("致PS新手：怎样才能真正学好PS", R.drawable.imga01));
        dataList.add(new ArticleItem("秋韵坝上&nbsp;给你一片金黄色的梦境", R.drawable.imga02));
        dataList.add(new ArticleItem("【领绣】家让灵魂自由旅行，如何设计才能做到呢？", R.drawable.imga03));
        dataList.add(new ArticleItem("哈苏X1D&nbsp;我先帮大家试试", R.drawable.imga04));

        lv = (ListView)findViewById(R.id.lv);
        //动态加载顶部View和底部View
        final LayoutInflater inflater = LayoutInflater.from(this);
        View headView = inflater.inflate(R.layout.header_view, null, false);
        View footView = inflater.inflate(R.layout.header_view, null, false);

        lv.addHeaderView(headView);
        lv.addFooterView(footView);

        myAdapter = new MyAdapter();
        lv.setAdapter(myAdapter);

        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(getApplicationContext(),"你点击了第" + position + "项",Toast.LENGTH_SHORT).show();
        // 添加一个item
//        dataList.add(new ArticleItem("【领绣】家让灵魂自由旅行，如何设计才能做到呢？", R.drawable.imga03));
        // 在某一个位置添加一个item
        // dataList.add(2,new ArticleItem("【领绣】家让灵魂自由旅行，如何设计才能做到呢？", R.drawable.imga03))
        // 更新某一行的item数据
        updateListItem(1,new ArticleItem("【领绣】家让灵魂自由旅行，如何设计才能做到呢？", R.drawable.imga03));
        myAdapter.notifyDataSetChanged();
    }

    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;

            if (convertView == null){
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_list_article,parent,false);
                holder = new ViewHolder();
                holder.iconImage = (ImageView) convertView.findViewById(R.id.list_item_iv);
                holder.titleName = (TextView) convertView.findViewById(R.id.list_item_tv);
                convertView.setTag(holder);   //将Holder存储到convertView中
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.iconImage.setBackgroundResource(dataList.get(position).getImage());
            holder.titleName.setText(dataList.get(position).getTitle());
            return convertView;
        }
    }

    static class ViewHolder{
        ImageView iconImage;
        TextView titleName;
    }

    private void updateListItem(int postion,ArticleItem item){
        int visiblePosition = lv.getFirstVisiblePosition();
        View v = lv.getChildAt(postion - visiblePosition);
        ImageView img = (ImageView) v.findViewById(R.id.list_item_iv);
        TextView tv = (TextView) v.findViewById(R.id.list_item_tv);
        img.setImageResource(item.getImage());
        tv.setText(item.getTitle());
    }
}
