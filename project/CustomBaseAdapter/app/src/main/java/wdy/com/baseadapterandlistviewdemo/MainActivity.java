package wdy.com.baseadapterandlistviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ArrayList<ArticleItem> dataList = new ArrayList<ArticleItem>();
    private ArrayList<ArticleItem> dataList2 = new ArrayList<ArticleItem>();
    private ListView lv;
    private ListView lv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataList.add(new ArticleItem("致PS新手：怎样才能真正学好PS", R.drawable.imga01));
        dataList.add(new ArticleItem("【领绣】家让灵魂自由旅行，如何设计才能做到呢？", R.drawable.imga03));
        dataList.add(new ArticleItem("哈苏X1D&nbsp;我先帮大家试试", R.drawable.imga04));

        dataList2.add(new ArticleItem("致PS新手：怎样才能真正学好PS", R.drawable.imga01));
        dataList2.add(new ArticleItem("秋韵坝上&nbsp;给你一片金黄色的梦境", R.drawable.imga03));

        lv = (ListView)findViewById(R.id.lv);
        lv2 = (ListView)findViewById(R.id.lv2);

        MyAdapter adapter1 = new MyAdapter<ArticleItem>(dataList, R.layout.item_list_article) {
            @Override
            public void bindView(ViewHolder holder, ArticleItem obj) {
                holder.setImageResource(R.id.list_item_iv,obj.getImage());
                holder.setText(R.id.list_item_tv,obj.getTitle());
            }
        };


        MyAdapter adapter2 = new MyAdapter<ArticleItem>(dataList2, R.layout.item_list_article2) {
            @Override
            public void bindView(ViewHolder holder, ArticleItem obj) {
                holder.setImageResource(R.id.list_item_iv,obj.getImage());
                holder.setText(R.id.list_item_tv,obj.getTitle());
            }
        };

        lv.setAdapter(adapter1);
        lv2.setAdapter(adapter2);
    }
}
