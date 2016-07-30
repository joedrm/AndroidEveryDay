package wdy.com.myandroidframework;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

/**
 * 12个页签的页面对象
 * 
 * @author Kevin
 * @date 2015-8-11
 */
public class TabDetailPager extends BasePager {

	private String title;
	private TextView mTextView;

	public TabDetailPager(Activity activity, String title) {
		super(activity);
		title = title;
	}

	@Override
	public void initView() {
		mTextView = new TextView(mActivity);
		mTextView.setTextColor(Color.RED);
		mTextView.setTextSize(22);
		mTextView.setGravity(Gravity.CENTER);

	}

	@Override
	public void initData() {

		mTextView.setText(title);
	}

}
