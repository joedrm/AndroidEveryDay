package wdy.com.myandroidframework;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class ContentFramgment extends BaseFramgment {

    public ViewPager mViewPager;
    private ArrayList<BasePager> mPages;
    private RadioGroup rg_group;
    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_content,null);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_content);
        rg_group = (RadioGroup)view.findViewById(R.id.rg_group);
        return view;
    }

    @Override
    public void initData() {
        mPages = new ArrayList<BasePager>();
        mPages.add(new HomePager(mActivity));
        mPages.add(new NewsCenterPager(mActivity));
        mPages.add(new SmartPager(mActivity));
        mPages.add(new SettingPager(mActivity));

        mViewPager.setAdapter(new ContenAdapater());

        // 标签点击事件
        rg_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_home:
                        mViewPager.setCurrentItem(0, false);
                        mPages.get(0).initData();
                        break;
                    case R.id.rb_news:
                        mViewPager.setCurrentItem(1, false);
                        mPages.get(1).initData();
                        break;
                    case R.id.rb_smart:
                        mViewPager.setCurrentItem(2, false);
                        mPages.get(2).initData();
                        break;
                    case R.id.rb_setting:
                        mViewPager.setCurrentItem(3, false);
                        mPages.get(3).initData();
                        break;
                }
            }
        });
        mPages.get(0).initData();
    }

    class ContenAdapater extends PagerAdapter{

        @Override
        public int getCount() {
            return mPages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager pager = mPages.get(position);
            container.addView(pager.mRootView);
            return pager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }
}
