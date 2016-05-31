package com.bridgelabz.recycleviewdemo.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bridgelabz.recycleviewdemo.R;
import com.bridgelabz.recycleviewdemo.animation.RecycleviewAnimation;
import com.bridgelabz.recycleviewdemo.databinding.AdapterRecycleViewBinding;
import com.bridgelabz.recycleviewdemo.model.User;

import java.util.ArrayList;

/**
 * Created by bridgelabz5 on 20/5/16.
 */
public class RecycleViewAdapter  extends RecyclerView.Adapter<RecycleViewAdapter.UserViewHolder>{
    ArrayList<User> list;
    private  int previous=0;

    public RecycleViewAdapter(ArrayList<User> list) {
        this.list=list;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       /*View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_recycle_view,parent,false);
        return new UserViewHolder(item);*/

        AdapterRecycleViewBinding item= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.adapter_recycle_view,parent,false);
        return new UserViewHolder(item);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
//        holder.name.setText(list.get(position).getName());
//        holder.age.setText(""+list.get(position).getAge());

        holder.onBindConnection(list.get(position));

        if(position>previous)
            RecycleviewAnimation.animated(holder,true);
        else
            RecycleviewAnimation.animated(holder,false);

        previous=position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

   class UserViewHolder extends RecyclerView.ViewHolder {
        AdapterRecycleViewBinding binding;
        public UserViewHolder(AdapterRecycleViewBinding binding) {
            super(binding.getRoot());
            this.binding=binding;

        }

       public void onBindConnection(User user){
            binding.setUser(user);
       }
    }

}
