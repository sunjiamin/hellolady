package com.sun.hellolady.demo.MeiNv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sun.hellolady.R;
import com.support.util.imageloader.ImageLoader;
import com.support.util.imageloader.ImageLoadingListener;
import com.support.util.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class Main3Activity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private MyRecAdapter myRecAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    int pageNu=1;
    static List<GankMeiziInfo> imgList = new ArrayList<GankMeiziInfo>();
    //static List<DataModel.NewslistEntity> imgListTemp = new ArrayList<DataModel.NewslistEntity>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeLayout);

        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);

        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setOnRefreshListener(this);



        RecyclerView rev = (RecyclerView)findViewById(R.id.rec_img_list);
        //rev.setLayoutManager(new LinearLayoutManager(this));
        //设置layoutManager
        rev.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        //设置adapter

        myRecAdapter = new MyRecAdapter();
        rev.setAdapter(myRecAdapter);

        LoadData();

        final ImageView img = (ImageView)findViewById(R.id.demo_img);

    }

    @Override
    public void onRefresh() {
        LoadData();
    }

    private void LoadData(){
        OkHttpUtils
                .get()//
                .url("http://gank.io/api/data/福利/30/"+pageNu)//
                .build()//
                .execute(new DataModelCallback() {
                    @Override
                    public GankMeiziResult parseNetworkResponse(Response response) throws Exception {
                        return super.parseNetworkResponse(response);
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        super.onError(call, e);
                    }

                    @Override
                    public void onResponse(final GankMeiziResult response) {
                        imgList.addAll(response.gankMeizis);
                        myRecAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
        pageNu++;
    }

       class MyRecAdapter extends RecyclerView.Adapter<MyHowerView>{


        @Override
        public MyHowerView onCreateViewHolder(ViewGroup parent, int viewType) {
            MyHowerView myHowerView = new MyHowerView(
                    LayoutInflater.from(
                            parent.getContext()).inflate(R.layout.rec_mei_item,parent,false));
            return myHowerView;
        }

        @Override
        public void onBindViewHolder(MyHowerView holder, int position) {
            holder.OnBind(holder,imgList.get(position),position);
        }

        @Override
        public int getItemCount() {
            return imgList.size();
        }
    }

    private   class MyHowerView extends RecyclerView.ViewHolder{
        PLAImageView iv ;
        TextView tv;
        public MyHowerView(View v){
            super(v);
            iv = (PLAImageView)v.findViewById(R.id.item_img);
           // tv = (TextView)v.findViewById(R.id.item_id);
            //v.setOnClickListener();
        }
        public void OnBind(final MyHowerView holder, final GankMeiziInfo data,final int position){
            //tv.setText(data.getTitle());
            holder.iv.setImageDrawable(holder.iv.getContext().getResources().getDrawable(R.drawable.ic_gender_male));
            ImageLoader.loadImage(holder.iv.getContext(),
                    data.url, holder.iv, null, new ImageLoadingListener() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            //
                            ImageLoader.loadImage(holder.iv.getContext(),
                                    "http://d.hiphotos.baidu.com/image/pic/item/0ff41bd5ad6eddc4aa295b333bdbb6fd53663328.jpg", holder.iv, null);
                        }
                    });
           // holder.iv.setImageHeight(data.getImageHeight());
            //holder.iv.setImageWidth(data.getImageWidth());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(holder.iv.getContext(), ImageDetail.class);
                    intent.putExtra("Image_Url", data.desc);


                    startActivity(intent);
                    overridePendingTransition(0, 0);



                }
            });
        }
    }
}
