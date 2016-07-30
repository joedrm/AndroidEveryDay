package wdy.com.myandroidframework;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by wdy on 16/6/19.
 */
public abstract class BasePager {

    public Activity mActivity;
    public View mRootView;
    public TextView tvTitle;
    public FrameLayout frameLayout;
    public BasePager (Activity activity){

        mActivity = activity;
        initView();
    }

    public void initView(){
        mRootView = View.inflate(mActivity, R.layout.base_pager, null);
        tvTitle =  (TextView)mRootView.findViewById(R.id.tv_title);
        frameLayout = (FrameLayout)mRootView.findViewById(R.id.fl_content);
    }

    // 初始化数据
    public abstract void initData();
}
