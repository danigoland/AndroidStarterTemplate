package com.undot.androidtemplate.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.undot.androidtemplate.MyApp;
import com.undot.androidtemplate.R;
import com.undot.androidtemplate.interfaces.EndlessRecyclerViewOnScrollListener;
import com.undot.androidtemplate.models.RVItem;
import com.undot.androidtemplate.views.adapters.SampleRecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecycleViewActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.rv_refresh)
    public SwipeRefreshLayout refreshLayout;

    @Bind(R.id.sample_recycleView)
    public RecyclerView recyclerView;

    private EndlessRecyclerViewOnScrollListener scrollListener;
    LinearLayoutManager layoutManager;

    private SampleRecycleViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        ButterKnife.bind(this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        scrollListener = new EndLessRecycleViewListener(layoutManager, 5);
        recyclerView.addOnScrollListener(scrollListener);
        refreshLayout.setOnRefreshListener(this);
        List<RVItem> list = new ArrayList<>();
        list.add(new RVItem("number one","http://www.clipartbest.com/cliparts/Kin/e4d/Kine4dK5T.jpeg"));
        list.add(new RVItem("number two","https://pixabay.com/static/uploads/photo/2015/09/23/10/20/car-953357_960_720.png"));

        adapter = new SampleRecycleViewAdapter(list, MyApp.getContext());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
            refreshLayout.setRefreshing(false);
    }

    class EndLessRecycleViewListener extends EndlessRecyclerViewOnScrollListener {

        public EndLessRecycleViewListener(LinearLayoutManager linearLayoutManager, int visibleThreshold) {
            super(linearLayoutManager, visibleThreshold);
        }

        @Override
        public void onLoadMore() {


            }

        }

}
