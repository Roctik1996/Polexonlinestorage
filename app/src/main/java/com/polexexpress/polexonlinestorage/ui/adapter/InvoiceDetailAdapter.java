package com.polexexpress.polexonlinestorage.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polexexpress.polexonlinestorage.model.Good;
import com.polexexpress.polexonlinestorage.R;

import java.util.ArrayList;

public class InvoiceDetailAdapter extends RecyclerView.Adapter<InvoiceDetailAdapter.InvoiceViewHolder> {

    private ArrayList<Good> data;
    public Context context;
    private String currency;

    public InvoiceDetailAdapter(Context context, ArrayList<Good> data, String currency) {
        this.context = context;
        this.data = data;
        this.currency = currency;
    }

    @NonNull
    @Override
    public InvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.invoice_detail_list, viewGroup, false);
        return new InvoiceViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull InvoiceViewHolder invoiceViewHolder, int i) {
        invoiceViewHolder.price.setText(currency+" "+data.get(i).getUnitValue()*data.get(i).getQuantity());
        invoiceViewHolder.info.setText("pl. "+data.get(i).getPlaceNumber()+"; pk. "+data.get(i).getPackageNumber()+"; qty. "+
                data.get(i).getQuantity()+"; m. "+data.get(i).getMeasure()+"; w. "+data.get(i).getWeight()+
                "; v. "+data.get(i).getUnitValue());
        invoiceViewHolder.num.setText(data.get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    static class InvoiceViewHolder extends RecyclerView.ViewHolder {

        TextView num;
        TextView info;
        TextView price;

        InvoiceViewHolder(@NonNull View itemView) {
            super(itemView);
            num = itemView.findViewById(R.id.item_num);
            info = itemView.findViewById(R.id.item_info);
            price = itemView.findViewById(R.id.item_price);
        }
    }
}
