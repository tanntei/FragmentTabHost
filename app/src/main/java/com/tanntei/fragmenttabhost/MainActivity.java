package com.tanntei.fragmenttabhost;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener {

    private FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;
    private List<Tab> tabs = new ArrayList<>(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutInflater = LayoutInflater.from(this);

        //初始化FragmentTabHost
        mTabHost = findViewById(android.R.id.tabhost);  //找到TabHost选项卡
        //mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);  //选项卡容器
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);  //关联TabHost
        mTabHost.setOnTabChangedListener(this);  //设置监听

        //实例化Tab对象
        Tab tab_home = new Tab(FragmentOne.class,R.string.home,R.drawable.home_selector);
        Tab tab_category = new Tab(FragmentTwo.class,R.string.category,R.drawable.category_selector);
        Tab tab_following = new Tab(FragmentThree.class,R.string.following,R.drawable.following_selector);
        Tab tab_mall = new Tab(FragmentFour.class,R.string.mall,R.drawable.mall_selector);

        //将对象加到一个List中
        tabs.add(tab_home);
        tabs.add(tab_category);
        tabs.add(tab_following);
        tabs.add(tab_mall);

        for (int i = 0; i < tabs.size() ; i ++ ) {

            View view = layoutInflater.inflate(R.layout.tab_item,null);  //设置自定义Tab视图
            ImageView imageView = view.findViewById(R.id.imageview);
            TextView textView = view.findViewById(R.id.textview);

            imageView.setImageResource(tabs.get(i).getIcon());
            textView.setText(tabs.get(i).getTitle());
            mTabHost.addTab(mTabHost.newTabSpec(getString(tabs.get(i).getTitle())).setIndicator(view),tabs.get(i).getFragment(),null);

            //设置底部菜单按钮背景
            //mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.);
        }
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);  //去除分割线
        mTabHost.setCurrentTab(0);  //设置默认选中图标

/*
        mTabHost.addTab(mTabHost.newTabSpec("simple").setIndicator("Simple"),
                FragmentStackSupport.CountingFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("contacts").setIndicator("Contacts"),
                LoaderCursorSupport.CursorLoaderListFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("custom").setIndicator("Custom"),
                LoaderCustomSupport.AppListFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("throttle").setIndicator("Throttle"),
                LoaderThrottleSupport.ThrottledLoaderListFragment.class, null);
*/
    }

    @Override
    public void onTabChanged(String tabId) {

        for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
            TextView tv = mTabHost.getTabWidget().getChildAt(i).findViewById(R.id.textview);
            ImageView iv = mTabHost.getTabWidget().getChildAt(i).findViewById(R.id.imageview);
            if (mTabHost.getCurrentTab() == i) {
                tv.setTextColor(this.getResources().getColor(R.color.tab_selected));
                iv.setColorFilter(this.getResources().getColor(R.color.tab_selected));
            } else {
                tv.setTextColor(this.getResources().getColor(R.color.tab_unselected));
                iv.setColorFilter(this.getResources().getColor(R.color.tab_unselected));
            }
        }
    }


}
