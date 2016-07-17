package com.radicalninja.transitwear.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public abstract class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    abstract void onItemClick(View view, int position);
    abstract void onItemLongClick(View view, int position);

    private final GestureDetector gestureDetector;

    private RecyclerView recyclerView;

    public RecyclerItemClickListener(Context context) {
        gestureDetector = new GestureDetector(context, new ItemClickGestureListener());
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        if (view != recyclerView) {
            recyclerView = view;
        }

        final View childView = view.findChildViewUnder(e.getX(), e.getY());

        if (childView != null && gestureDetector.onTouchEvent(e)) {
            onItemClick(childView, view.getChildAdapterPosition(childView));
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) {
        //
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        //
    }

    class ItemClickGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            final View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());

            if (childView != null) {
                onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView));
            }
        }
    }

}
