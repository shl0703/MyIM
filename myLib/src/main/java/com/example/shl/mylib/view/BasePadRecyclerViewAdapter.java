package com.example.shl.mylib.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BasePadRecyclerViewAdapter extends RecyclerView.Adapter {
    //    protected BaseRecyclerView.OnItemSelectedListener onItemSelectedListener;
    protected BaseRecyclerView.OnItemClickListener onItemClickListener;

    public BaseRecyclerView.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(BaseRecyclerView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

//    public void setOnItemSelectedListener(BaseRecyclerView.OnItemSelectedListener onItemSelectedListener) {
//        this.onItemSelectedListener = onItemSelectedListener;
//    }
//
//    public BaseRecyclerView.OnItemSelectedListener getOnItemSelectedListener() {
//        return onItemSelectedListener;
//    }

    public class BaseRecyclerViewHolder extends BaseRecyclerView.ViewHolder implements View.OnClickListener {
        public BaseRecyclerView recyclerView;
        public int position;
        public View itemView;

        public BaseRecyclerViewHolder(BaseRecyclerView recyclerView, View itemView, int position) {
            super(itemView);
            this.recyclerView = recyclerView;
            this.position = position;
            this.itemView = itemView;
//            this.itemView.setFocusable(true);
//            this.itemView.setFocusableInTouchMode(true);
//            this.itemView.setClickable(true);
//            this.itemView.setOnFocusChangeListener(this);
            this.itemView.setOnClickListener(this);
        }

//        @Override
//        public void onFocusChange(View view, boolean b) {
//            if(b) {
//                recyclerView.setSelectedView(view);
//                recyclerView.setSelectedPosition(position);
//                if(onItemSelectedListener != null) {
//                    onItemSelectedListener.onItemSelected(recyclerView, view, position);
//                }
//            }
//        }

        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(recyclerView, view, position);
            }
        }
    }
}
