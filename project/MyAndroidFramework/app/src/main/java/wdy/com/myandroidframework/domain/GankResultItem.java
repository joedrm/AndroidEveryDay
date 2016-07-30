package wdy.com.myandroidframework.domain;

import java.util.ArrayList;

/**
 * Created by wdy on 16/6/20.
 */
public class GankResultItem {

    public int count;
    public Boolean error;
    public ArrayList<GankItem> results;


    public class GankItem{

        public String desc;
        public String publishedAt;
        public String readability;
        public String type;
        public String url;
        public String who;
    }

}
