package com.polexexpress.polexonlinestorage.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polexexpress.polexonlinestorage.R;
import com.polexexpress.polexonlinestorage.model.SearchInvoice;

import java.util.ArrayList;

import static com.polexexpress.polexonlinestorage.other.Const.getTypeIcon;

public class DetailGoodsAdapter extends RecyclerView.Adapter<DetailGoodsAdapter.DeliveryViewHolder> {

    private ArrayList<SearchInvoice> data;
    private static View.OnClickListener mOnItemClickListener;

    public DetailGoodsAdapter(ArrayList<SearchInvoice> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public DeliveryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.detail_goods_list, viewGroup, false);
        return new DeliveryViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DeliveryViewHolder deliveryViewHolder, int i) {
        deliveryViewHolder.count.setText(data.get(i).getPlaceNumber() + " мест");
        deliveryViewHolder.date.setText(data.get(i).getDate());
        deliveryViewHolder.num.setText(data.get(i).getTrackNo());
        deliveryViewHolder.type.setImageResource(getTypeIcon(data.get(i).getStatus()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    static class DeliveryViewHolder extends RecyclerView.ViewHolder {

        ImageView type;
        TextView num;
        TextView date;
        TextView count;

        DeliveryViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.item_icon);
            num = itemView.findViewById(R.id.item_num);
            date = itemView.findViewById(R.id.item_date);
            count = itemView.findViewById(R.id.item_count_places);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }
}
