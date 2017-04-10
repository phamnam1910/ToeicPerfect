package com.app.learningtoeic.ui.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.learningtoeic.R;
import com.app.learningtoeic.base.BaseFragment;
import com.app.learningtoeic.contract.DetailWordContract;
import com.app.learningtoeic.contract.DictionaryContract;
import com.app.learningtoeic.contract.HomeContract;
import com.app.learningtoeic.entity.Word;
import com.app.learningtoeic.mvp.fragment.MVPFragment;
import com.app.learningtoeic.presenter.DictionaryPresenter;
import com.app.learningtoeic.ui.adapter.WordsAdapter;
import com.app.learningtoeic.utils.Config;
import com.app.learningtoeic.utils.ProgressDialogHelper;
import com.app.learningtoeic.utils.WordDbHelper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 3/31/2017.
 */

public class DictionaryFragment extends MVPFragment<DictionaryContract.IPresenterViewOps> implements DictionaryContract.IViewOps,WordsAdapter.Callback
{
    ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    public WordsAdapter adapter;
    private WordDbHelper databaseHepler;

    public DictionaryFragment() {
        // Required empty public constructor
    }


    @Override
    protected void OnViewCreated() {
        adapter = new WordsAdapter();
        adapter.callback = this;
        recyclerView.setAdapter(adapter);
        getPresenter().ExcuteDictionaryTask();
    }

    @Override
    protected void OnBindView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView = (RecyclerView)FindViewById(R.id.recycle_view_vocabulary);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public int GetLayoutId() {
        return R.layout.dictionary_fragment;
    }

    @Override
    protected String GetScreenTitle() {
        return getString(R.string.app_name);
    }

    @Override
    protected DictionaryContract.IPresenterViewOps OnRegisterPresenter() {
        return new DictionaryPresenter();
    }

    @Override
    public void InsertData(ArrayList<Word> listItem) {
        adapter.InsertData(listItem);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void ShowProgressDialog() {
        progressDialog = ProgressDialog.show(getContext(), "", "File loading ...",false, false);
    }

    @Override
    public void HideProgessDialog() {
        progressDialog.dismiss();
    }

    @Override
    public void OnClickDetailItem(ArrayList<Word> wordList, int wordId) {
        SwitchFragment(new DetailWordFragment(wordList,wordId),true);
    }
}
