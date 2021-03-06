package com.app.learningtoeic.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.HomeContract;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.HomePresenter;
import com.app.learningtoeic.ui.adapter.HomePagerAdapter;
import com.app.learningtoeic.ui.fragment.dictionary.DictionaryFragment;
import com.app.learningtoeic.ui.fragment.test.TestFragment;
import com.app.learningtoeic.ui.fragment.topic.TopicFragment;

/**
 * Created by dell on 3/31/2017.
 */

public class HomeFragment extends MVPFragment<HomeContract.IPresenterViewOps> implements HomeContract.IViewOps, ViewPager.OnPageChangeListener, TabLayout.OnTabSelectedListener {
    TabLayout tabLayout;
    ViewPager mainViewPager;

    @Override
    protected HomeContract.IPresenterViewOps OnRegisterPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void OnViewCreated() {
        initToolBarWithTab();
    }

    void initToolBarWithTab() {
        setupViewPager();
        tabLayout.setupWithViewPager(mainViewPager);
        initTab();
    }

    void setupViewPager()
    {
        mainViewPager.setOffscreenPageLimit(1);
        HomePagerAdapter homePagerAdapter = new HomePagerAdapter(getChildFragmentManager());
        homePagerAdapter.addFrag(new TopicFragment(),"Topic");
        homePagerAdapter.addFrag(new TestFragment(),"Test");
        homePagerAdapter.addFrag(new DictionaryFragment(),"Dictionary");
        homePagerAdapter.addFrag(new MoreFragment(),"More");
        mainViewPager.setAdapter(homePagerAdapter);
    }

    void initTab()
    {
        tabLayout.getTabAt(0).select();
        tabLayout.addOnTabSelectedListener(this);
        tabLayout.setTabTextColors(
                ContextCompat.getColor(getContext(), R.color.black),
                ContextCompat.getColor(getContext(), R.color.white)
        );
    }

    @Override
    protected void OnBindView() {
        bindView();
    }

    private void bindView() {
        tabLayout = (TabLayout) FindViewById(R.id.tab_layout);
        mainViewPager = (ViewPager) FindViewById(R.id.tab_viewpager);
        mainViewPager.addOnPageChangeListener(this);
    }

    @Override
    public int GetLayoutId() {
        return R.layout.app_bar_main;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean IsMenuVisible() {
        return true;
    }

    @Override
    protected String GetScreenTitle() {
        return "Toeic Perfect";
    }
}

