package com.pagodalabs.materialdesign.listeners;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.pagodalabs.materialdesign.dao.RecyclerClassListener;

/**
 * Created by Ajit Kumar Baral on 6/13/2015.
 */
public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

    private GestureDetector gestureDetector;
    private RecyclerClassListener clickListener;

    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final RecyclerClassListener clickListener){
        this.clickListener = clickListener;

        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child =  recyclerView.findChildViewUnder(e.getX(), e.getY());
                Log.d("touch", "long touch");
                if(child!=null && clickListener!=null){
                    clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                }
            }
        });

    }


    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child =  rv.findChildViewUnder(e.getX(), e.getY());
        if(child!=null && clickListener!=null && gestureDetector.onTouchEvent(e)){
            clickListener.onClick(child, rv.getChildPosition(child));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }
}
