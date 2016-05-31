package com.bridgelabz.recycleviewdemo.animation;

import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;

/**
 * Created by bridgelabz5 on 21/5/16.
 */
public class RecycleviewAnimation {

    public static void animated(RecyclerView.ViewHolder holder,boolean goesDown){
        ObjectAnimator animator=ObjectAnimator.ofFloat(holder.itemView,"translationY",goesDown==true ? 100 :-100,0);
        animator.setDuration(1000);
        animator.start();
    }
}
