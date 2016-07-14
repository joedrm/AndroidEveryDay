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
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private ArrayList<ArticleItem> dataList = new ArrayList<ArticleItem>();
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataList.add(new ArticleItem("致PS新手：怎样才能真正学好PS", R.drawable.imga01));
        dataList.add(new ArticleItem("秋韵坝上&nbsp;给你一片金黄色的梦境", R.drawable.imga02));
        dataList.add(new ArticleItem("【领绣】家让灵魂自由旅行，如何设计才能做到呢？", R.drawable.imga03));
        dataList.add(new ArticleItem("哈苏X1D&nbsp;我先帮大家试试", R.drawable.imga04));
        dataList.add(new ArticleItem("阿根廷摄影大师&nbsp;痛苦而又不失尊严", R.drawable.imga05));
        dataList.add(new ArticleItem("寻找摄影师丨吴建新：紫鹊界，四季四美", R.drawable.imga06));
        dataList.add(new ArticleItem("2016“阿尔勒摄影节发现奖”揭晓，乌干达女摄影师夺魁！", R.drawable.imga07));
        dataList.add(new ArticleItem("致PS新手：怎样才能真正学好PS", R.drawable.imga01));
        dataList.add(new ArticleItem("秋韵坝上&nbsp;给你一片金黄色的梦境", R.drawable.imga02));
        dataList.add(new ArticleItem("【领绣】家让灵魂自由旅行，如何设计才能做到呢？", R.drawable.imga03));
        dataList.add(new ArticleItem("哈苏X1D&nbsp;我先帮大家试试", R.drawable.imga04));
        dataList.add(new ArticleItem("阿根廷摄影大师&nbsp;痛苦而又不失尊严", R.drawable.imga05));
        dataList.add(new ArticleItem("寻找摄影师丨吴建新：紫鹊界，四季四美", R.drawable.imga06));
        dataList.add(new ArticleItem("2016“阿尔勒摄影节发现奖”揭晓，乌干达女摄影师夺魁！", R.drawable.imga07));

        lv = (ListView)findViewById(R.id.lv);
        //动态加载顶部View和底部View
        final LayoutInflater inflater = LayoutInflater.from(this);
        View headView = inflater.inflate(R.layout.header_view, null, false);
        View footView = inflater.inflate(R.layout.header_view, null, false);

        lv.addHeaderView(headView);
        lv.addFooterView(footView);

        lv.setAdapter(new MyAdapter());

        lv.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(),"你点击了第" + position + "项",Toast.LENGTH_SHORT).show();
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
            convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_list_article,parent,false);
            ImageView img_icon = (ImageView) convertView.findViewById(R.id.list_item_iv);
            TextView txt_aName = (TextView) convertView.findViewById(R.id.list_item_tv);
            img_icon.setBackgroundResource(dataList.get(position).getImage());
            txt_aName.setText(dataList.get(position).getTitle());
            return convertView;
        }
    }
}
