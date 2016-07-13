package wdy.com.listviewexample;

/**
 * Created by wdy on 16/7/12.
 */
public class ArticleItem {

    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(int image) {
        this.image = image;
    }

    private String title;
    private int image;

    public ArticleItem(){

    }

    public ArticleItem(String title, int image){
        this.title = title;
        this.image = image;
    }
}
