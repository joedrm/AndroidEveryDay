package wdy.com.myandroidframework;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 去掉标题, 必须在setContentView之前执行
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFramLayout();
    }

    private void initFramLayout(){
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        // 开启事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // 将Activity的布局替换
        fragmentTransaction.replace(R.id.main_content, new ContentFramgment(),"TAG_CONTENT");
        // 提交事务
        fragmentTransaction.commit();
    }
}
