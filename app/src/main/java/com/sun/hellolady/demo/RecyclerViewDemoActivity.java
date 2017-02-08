package com.sun.hellolady.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sun.hellolady.R;
import com.support.util.SwipeRefreshRecyclerView.ABaseLinearLayoutManager;
import com.support.util.SwipeRefreshRecyclerView.OnRecyclerViewScrollListener;
import com.support.util.SwipeRefreshRecyclerView.OnRecyclerViewScrollLocationListener;
import com.support.util.common.T;
import com.support.util.common.ToastHelper;
import com.support.util.widget.MySwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewDemoActivity extends AppCompatActivity implements   SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    List<Person> personList;
    private PersonAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MyItemClickListener myItemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);

        swipeRefreshLayout = (MySwipeRefreshLayout)findViewById(R.id.swipeLayout);


        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setOnRefreshListener(this);

        final View footerView = LayoutInflater.from(this).
                inflate(R.layout.buttom_layout, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        footerView.setLayoutParams(lp);


        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_test_rv);
        recyclerView.setHasFixedSize(true);//使RecyclerView保持固定的大小,这样会提高RecyclerView的性能。
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        final ABaseLinearLayoutManager layoutManager = new ABaseLinearLayoutManager(this);
        layoutManager.setOnRecyclerViewScrollLocationListener(recyclerView, new OnRecyclerViewScrollLocationListener() {
            @Override
            public void onTopWhenScrollIdle(RecyclerView recyclerView) {
                ToastHelper.showToast(getApplicationContext(), "onTopWhenScrollIdle", Toast.LENGTH_SHORT);
            }

            @Override
            public void onBottomWhenScrollIdle(RecyclerView recyclerView) {
                //footerView.setVisibility(View.VISIBLE);
                ToastHelper.showToast(getApplicationContext(), "到底啦", Toast.LENGTH_SHORT);
            }
        });

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
              layoutManager.getRecyclerViewScrollManager().addScrollListener(recyclerView, new OnRecyclerViewScrollListener() {
                  @Override
                  public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                  }

                  @Override
                  public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                      swipeRefreshLayout.setEnabled(layoutManager.findFirstCompletelyVisibleItemPosition() == 0);
                  }
              });

        recyclerView.setLayoutManager(layoutManager);

        initiData();
        myItemClickListener = new MyItemClickListener() {
            @Override
            public void ItemClick(View v, int position) {
                T.show(RecyclerViewDemoActivity.this,position+" ",Toast.LENGTH_SHORT);
              switch (position){

                  case 0:
                      startActivity(new Intent(RecyclerViewDemoActivity.this,Service2Activity.class));
                      break;
                  case 1:
                      startActivity(new Intent(RecyclerViewDemoActivity.this,Service3Activity.class));
                      break;
                  case 2:
                      break;
                  default:
                      break;
              }
            }
        };
        adapter = new PersonAdapter(personList,myItemClickListener);

        recyclerView.setAdapter(adapter);

        //添加分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(
                this, DividerItemDecoration.HORIZONTAL_LIST));

    }

    private void initiData() {
        personList = new ArrayList<>();
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));
        personList.add(new Person("1","2"));







    }


    @Override
    public void onRefresh() {
        new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }) ;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

}
