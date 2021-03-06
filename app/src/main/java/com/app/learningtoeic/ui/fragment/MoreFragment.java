package com.app.learningtoeic.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.contract.MoreContract;
import com.app.learningtoeic.entity.MoreItem;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.MorePresenter;
import com.app.learningtoeic.ui.adapter.MoreAdapter;
import com.app.learningtoeic.ui.fragment.chat.SplashLoginFragment;
import com.app.learningtoeic.ui.fragment.grammar.GrammarFragment;
import com.app.learningtoeic.ui.fragment.test.HighScoreFragment;

import java.util.ArrayList;

/**
 * Created by dell on 4/7/2017.
 */

public class MoreFragment extends MVPFragment<MoreContract.IPresenterViewOps> implements MoreContract.IViewOps, MoreAdapter.Callback {

    private RecyclerView recyclerView;

    private ArrayList<MoreItem> mMoreList;
    private MoreAdapter adapter;

    public MoreFragment(){
        mMoreList = new ArrayList<>();
    }

    @Override
    protected void OnViewCreated() {
        adapter = new MoreAdapter();
        adapter.callback = this;
        recyclerView.setAdapter(adapter);
        getPresenter().InitListItem();
    }

    @Override
    protected void OnBindView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView) FindViewById(R.id.rcv_more);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public int GetLayoutId() {
        return R.layout.more_fragment;
    }

    @Override
    protected String GetScreenTitle() {
        return "Toeic Perfect";
    }

    @Override
    protected MoreContract.IPresenterViewOps OnRegisterPresenter() {
        return new MorePresenter();
    }

    @Override
    public void InsertDataToAdapter(ArrayList<MoreItem> listItem) {
        adapter.InsertData(listItem);
    }

    @Override
    public void OnMoreItemClick(int position) {
        switch (position){
            case 0:
                SwitchFragment(new GrammarFragment(),true);
                break;
            case 1:
                SwitchFragment(new SplashLoginFragment(),true);
                break;
            case 2:
                SwitchFragment(new HighScoreFragment(),true);
                break;
            case 3:
                SwitchFragment(new FavouriteFragment(), true);
                break;
            default:
                break;
        }
    }
}
