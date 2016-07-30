package wdy.com.myandroidframework;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by wdy on 16/6/19.TabDetailPager
 */
public class NewsCenterPager extends BasePager{



    public NewsCenterPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tvTitle.setText("消息");

        TextView textView = new TextView(mActivity);
        textView.setText("消息");
        textView.setTextColor(Color.RED);
        textView.setTextSize(22);
        textView.setGravity(Gravity.CENTER);
        frameLayout.addView(textView);
    }


}
