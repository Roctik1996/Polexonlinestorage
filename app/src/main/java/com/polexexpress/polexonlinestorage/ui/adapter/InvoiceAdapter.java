package com.polexexpress.polexonlinestorage.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polexexpress.polexonlinestorage.R;
import com.polexexpress.polexonlinestorage.model.SearchInvoice;

import java.util.ArrayList;

public class InvoiceAdapter extends RecyclerView.Adapter {

    private ArrayList<SearchInvoice> data;
    private static View.OnClickListener mOnItemClickListener;
    private final int VIEW_TYPE_ITEM = 0;

    public InvoiceAdapter(ArrayList<SearchInvoice> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.invoices_list, parent, false);
            return new InvoiceViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(v);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        if (holder instanceof InvoiceViewHolder) {
            populateItemRows((InvoiceViewHolder) holder, i);

        } else if (holder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) holder, i);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public int getItemViewType(int position) {
        int VIEW_TYPE_LOADING = 1;
        return data.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    static class InvoiceViewHolder extends RecyclerView.ViewHolder {

        TextView num;
        TextView date;
        TextView count;

        InvoiceViewHolder(@NonNull View itemView) {
            super(itemView);
            num = itemView.findViewById(R.id.item_num);
            date = itemView.findViewById(R.id.item_date);
            count = itemView.findViewById(R.id.item_count_places);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress_loader);
        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed

    }

    @SuppressLint("SetTextI18n")
    private void populateItemRows(InvoiceViewHolder viewHolder, int position) {

        if (data.get(position).getPlaceNumber() != null)
            viewHolder.count.setText(data.get(position).getPlaceNumber() + " мест");
        else
            viewHolder.count.setText("Пусто");
        viewHolder.date.setText(data.get(position).getDate());
        viewHolder.num.setText(data.get(position).getTrackNo());

    }
}
