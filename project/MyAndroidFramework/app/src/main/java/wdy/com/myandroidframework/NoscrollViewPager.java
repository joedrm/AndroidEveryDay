package wdy.com.myandroidframework;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by wdy on 16/6/20.
 */
public class NoscrollViewPager extends ViewPager {
    public NoscrollViewPager(Context context) {
        super(context);
    }

    public NoscrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 禁用掉ViewPager的出门事件
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }
}
