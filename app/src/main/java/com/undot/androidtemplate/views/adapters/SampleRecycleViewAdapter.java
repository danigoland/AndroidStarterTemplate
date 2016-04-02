package com.undot.androidtemplate.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.undot.androidtemplate.R;
import com.undot.androidtemplate.models.RVItem;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 0503337710 on 02/04/2016.
 */
public class SampleRecycleViewAdapter extends RecyclerView.Adapter<SampleRecycleViewAdapter.DataObjectHolder> {

    private List<RVItem> mDataset;
    private static MyClickListener myClickListener;
    private Context context;
    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        @Bind(R.id.rv_text)
        TextView label;
        @Bind(R.id.rv_image)
        ImageView image;


        public DataObjectHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            image.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }
    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public SampleRecycleViewAdapter(List<RVItem> myDataset,Context context) {
        this.context = context;
        mDataset = myDataset;
    }
    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_item, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }
    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        holder.label.setText(mDataset.get(position).getName());
        Picasso.with(context).load(mDataset.get(position).getUrl()).into(holder.image);

    }
    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
    public RVItem removeItem(int position) {
        final RVItem model = mDataset.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, RVItem model) {
        mDataset.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final RVItem model = mDataset.remove(fromPosition);
        mDataset.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    private void applyAndAnimateRemovals(List<RVItem> newModels) {
        for (int i = mDataset.size() - 1; i >= 0; i--) {
            final RVItem model = mDataset.get(i);

            if (!newModels.contains(model)) {
                removeItem(i);
            }

        }
    }
    public void animateTo(List<RVItem> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }
    private void applyAndAnimateAdditions(List<RVItem> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final RVItem model = newModels.get(i);

            if (!mDataset.contains(model)) {
                addItem(i, model);
            }
        }
    }
    private void applyAndAnimateMovedItems(List<RVItem> newModels) {
        if (newModels != null) {
            for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
                final RVItem model = newModels.get(toPosition);
                final int fromPosition = mDataset.indexOf(model);
                if (fromPosition >= 0 && fromPosition != toPosition) {
                    moveItem(fromPosition, toPosition);
                }
            }
        }
    }


    public RVItem getItem(int index)
    {
        return mDataset.get(index);
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

   
}
