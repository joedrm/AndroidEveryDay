package wdy.com.myandroidframework.domain;

import java.util.ArrayList;

/**
 * Created by wdy on 16/6/25.
 */
public class ZhihuLatest {

    public String date;
    public ArrayList<ZhihuNewsItem> stories;
    public ArrayList<ZhihuNewsItem> top_stories;

    public class ZhihuNewsItem{

        public String title;
        public String ga_prefix;
        public String image;
        public String[] images;
        public int type;
        public int id;
    }
}
