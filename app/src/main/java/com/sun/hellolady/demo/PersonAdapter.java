package com.sun.hellolady.demo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sun.hellolady.R;

import java.util.List;

/**
 * Created by Jiamin.Sun on 1/25/2016.
 */
public class PersonAdapter extends RecyclerView.Adapter{

    private List<Person> list;

    private MyItemClickListener myItemClickListener;

    public PersonAdapter(List<Person> personList,MyItemClickListener myItemClickListener) {
        this.list=personList;
        this.myItemClickListener=myItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_demo_item, null);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new PersonViewHolder(view,myItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, int i) {
        PersonViewHolder holder = (PersonViewHolder) holder1;
        holder.position = i;
        Person person = list.get(i);
        holder.nameTv.setText(person.getName());
        holder.ageTv.setText(person.getAge() + "岁");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public View rootView;
        public TextView nameTv;
        public TextView ageTv;
        public int position;

        public MyItemClickListener myItemClickListener;
        public PersonViewHolder(View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.recycler_view_test_item_person_name_tv);
            ageTv = (TextView) itemView.findViewById(R.id.recycler_view_test_item_person_age_tv);
            rootView = itemView.findViewById(R.id.recycler_view_test_item_person_view);
            rootView.setOnClickListener(this);

        }
        public PersonViewHolder(View itemView ,MyItemClickListener clickListener) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.recycler_view_test_item_person_name_tv);
            ageTv = (TextView) itemView.findViewById(R.id.recycler_view_test_item_person_age_tv);
            rootView = itemView.findViewById(R.id.recycler_view_test_item_person_view);
            rootView.setOnClickListener(this);
            this.myItemClickListener=clickListener;
           // rootView.setOnClickListener(this);
           // rootView.setOnLongClickListener(this);
        }


        @Override
        public void onClick(View v) {
           if(myItemClickListener!=null){
               myItemClickListener.ItemClick(v,getAdapterPosition());
            }

        }
    }

}

