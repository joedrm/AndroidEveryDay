package wdy.com.myandroidframework;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by wdy on 16/6/19.
 */
public class SmartPager extends BasePager {

    public SmartPager(Activity activity) {
        super(activity);
    }

    @Override
    public void initData() {
        tvTitle.setText("发现");

        TextView textView = new TextView(mActivity);
        textView.setText("发现");
        textView.setTextColor(Color.RED);
        textView.setTextSize(22);
        textView.setGravity(Gravity.CENTER);
        frameLayout.addView(textView);
    }
}
