package com.polexexpress.polexonlinestorage.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.polexexpress.polexonlinestorage.R;
import com.polexexpress.polexonlinestorage.model.ContentDetail;
import com.polexexpress.polexonlinestorage.model.DetailGroupInvoice;
import com.polexexpress.polexonlinestorage.model.Filter;
import com.polexexpress.polexonlinestorage.model.Group;
import com.polexexpress.polexonlinestorage.model.Login;
import com.polexexpress.polexonlinestorage.model.PagingInfo;
import com.polexexpress.polexonlinestorage.model.SearchInvoice;
import com.polexexpress.polexonlinestorage.model.SortInfo;
import com.polexexpress.polexonlinestorage.model.StatusCheckInvoice;
import com.polexexpress.polexonlinestorage.other.Const;
import com.polexexpress.polexonlinestorage.presenter.DeliveryDetailPresenter;
import com.polexexpress.polexonlinestorage.ui.adapter.DetailGoodsAdapter;
import com.polexexpress.polexonlinestorage.view.DeliveryDetailView;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

import static com.polexexpress.polexonlinestorage.other.Const.getTypeIcon;

public class DetailGoodsActivity extends MvpAppCompatActivity implements DeliveryDetailView {

    private RecyclerView recyclerView;
    private ArrayList<SearchInvoice> data;
    private ImageView icon_type;
    private TextView title, txt_awb, txtName, txtAddress, txtDate, txtPlace, txtWeight, txtCountry, txtCost;
    private String token;

    @InjectPresenter
    DeliveryDetailPresenter deliveryDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_goods);
        data = new ArrayList<>();
        Toolbar toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler_detail_goods);
        title = findViewById(R.id.title_detail_toolbar);
        txt_awb = findViewById(R.id.txt_num);
        txtName = findViewById(R.id.txt_info_title);
        txtAddress = findViewById(R.id.txt_info_body);
        txtDate = findViewById(R.id.txt_detail_date);
        txtPlace = findViewById(R.id.txt_detail_count);
        txtWeight = findViewById(R.id.txt_detail_weight);
        txtCountry = findViewById(R.id.txt_detail_from);
        icon_type = findViewById(R.id.icon_type);
        txtCost = findViewById(R.id.txt_detail_cost);

        ImageView check = findViewById(R.id.icon_check_detail);
        ImageView upload = findViewById(R.id.icon_upload_detail);
        ImageView cancel = findViewById(R.id.icon_cancel_detail);

        TextView txtCheck = findViewById(R.id.txt_detail_check);
        TextView txtUpload = findViewById(R.id.txt_detail_upload);
        TextView txtCancel = findViewById(R.id.txt_detail_cancel);

        check.setOnClickListener(view -> {
            Intent scan = new Intent(this, CheckInvoiceActivity.class);
            scan.putExtra("groupId", getIntent().getStringExtra("id"));
            startActivity(scan);
        });

        txtCheck.setOnClickListener(view -> {
            Intent scan = new Intent(this, CheckInvoiceActivity.class);
            scan.putExtra("groupId", getIntent().getStringExtra("id"));
            startActivity(scan);
        });

        upload.setOnClickListener(view -> {
            Intent scan = new Intent(this, ShipmentActivity.class);
            scan.putExtra("groupId", getIntent().getStringExtra("id"));
            startActivity(scan);
        });

        txtUpload.setOnClickListener(view -> {
            Intent scan = new Intent(this, ShipmentActivity.class);
            scan.putExtra("groupId", getIntent().getStringExtra("id"));
            startActivity(scan);
        });

        cancel.setOnClickListener(view -> {
            deliveryDetailPresenter.refreshToken(Const.grant_type_refresh, Const.refresh_token);
            token = Const.token_type + " " + Const.token;
            deliveryDetailPresenter.finishShipment(token,getIntent().getStringExtra("id"));
        });

        txtCancel.setOnClickListener(view -> {
            deliveryDetailPresenter.refreshToken(Const.grant_type_refresh, Const.refresh_token);
            token = Const.token_type + " " + Const.token;
            deliveryDetailPresenter.finishShipment(token,getIntent().getStringExtra("id"));
        });



        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());

        deliveryDetailPresenter.refreshToken(Const.grant_type_refresh, Const.refresh_token);
        token = Const.token_type + " " + Const.token;
        deliveryDetailPresenter.getGroupDetail(token, getIntent().getStringExtra("id"));

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);


    }

    @Override
    public void getGroupInvoice(DetailGroupInvoice detailGroupInvoice) {
        data.addAll(detailGroupInvoice.getContentDetailGroupInvoice().getData());
        DetailGoodsAdapter adapter = new DetailGoodsAdapter(data);
        adapter.setOnItemClickListener(onItemClickListener);
        recyclerView.setAdapter(adapter);

    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Intent detail = new Intent(getApplicationContext(), InvoiceDetailActivity.class);
            detail.putExtra("track_no", data.get(position).getTrackNo());
            startActivity(detail);
        }
    };

    @SuppressLint("SetTextI18n")
    @Override
    public void getGroupDetail(ContentDetail content) {
        Group group = new Group();
        Filter filter = new Filter();
        filter.setAwbNo(content.getData().getAwb());
        group.setFilter(filter);

        PagingInfo pagingInfo = new PagingInfo();
        pagingInfo.setPageNum(0);
        pagingInfo.setPageSize(10);
        group.setPagingInfo(pagingInfo);

        ArrayList<SortInfo> sortInfos = new ArrayList<>();
        SortInfo sortInfo = new SortInfo();
        sortInfo.setFieldName("id");
        sortInfo.setSortDirection("desc");
        sortInfos.add(sortInfo);
        group.setSortInfos(sortInfos);
        deliveryDetailPresenter.getGroupDetailInvoice(token, group);
        title.setText(content.getData().getAwb());
        txt_awb.setText(content.getData().getAwb());
        if (content.getData().getStore() != null) {
            txtName.setText(content.getData().getStore().getName());
            txtAddress.setText(content.getData().getStore().getAddress());
        } else {
            txtName.setText("");
            txtAddress.setText("");
        }

        txtDate.setText(content.getData().getCreateDate());
        if (content.getData().getTotalPlaceNumber() != null)
            txtPlace.setText("Мест - " + content.getData().getTotalPlaceNumber());
        else
            txtPlace.setText("Мест - " + 0);

        if (content.getData().getTotalWeight() != null)
            txtWeight.setText("Общий вес - " + content.getData().getTotalWeight());
        else
            txtWeight.setText("Общий вес - " + 0);

        if (content.getData().getSenderCountry() != null && content.getData().getReceiverCountry() != null)
            txtCountry.setText(content.getData().getSenderCountry() + "->" + content.getData().getReceiverCountry());
        else
            txtCountry.setText("");
        StringBuilder total = new StringBuilder();
        if (content.getData().getCurrencies().size() > 0) {
            if (content.getData().getCurrencies().size() > 1) {
                for (int i = 0; i < content.getData().getCurrencies().size(); i++) {
                    total.append(Utils.getCurrencySymbol(content.getData().getCurrencies().get(i).getCurrency()))
                            .append(" ")
                            .append(content.getData().getCurrencies().get(i).getAmount())
                            .append(" ");
                }
                txtCost.setText("Сумма - " + total);
            } else {
                txtCost.setText("Сумма - " + Utils.getCurrencySymbol(content.getData().getCurrencies().get(0).getCurrency()) + " " + content.getData().getCurrencies().get(0).getAmount());
            }
        } else {
            txtCost.setText("Сумма - " + 0);
        }
        icon_type.setImageResource(getTypeIcon(content.getData().getGroupStatus()));
    }

    @Override
    public void finishShipment(StatusCheckInvoice status) {
        if (status.getError().getStatus()==200){
            finish();
            startActivity(getIntent());
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, "Произошла ошибка", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshToken(Login login) {
        Const.refresh_token = login.getRefreshToken();
        Const.token_type = login.getTokenType();
        Const.token = login.getAccessToken();
    }

    @Override
    public void showProgress(boolean isLoading) {
        FrameLayout frameLayout = findViewById(R.id.frame);
        ProgressBar progressBar = findViewById(R.id.loader);
        if (isLoading) {
            frameLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            frameLayout.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private static class Utils {
        private static SortedMap<Currency, Locale> currencyLocaleMap;

        static {
            currencyLocaleMap = new TreeMap<>((c1, c2) -> c1.getCurrencyCode().compareTo(c2.getCurrencyCode()));
            for (Locale locale : Locale.getAvailableLocales()) {
                try {
                    Currency currency = Currency.getInstance(locale);
                    currencyLocaleMap.put(currency, locale);
                } catch (Exception e) {
                    Log.d("DetailGoodsActivity", Objects.requireNonNull(e.getMessage()));
                }
            }
        }

        private static String getCurrencySymbol(String currencyCode) {
            if (currencyCode != null) {
                Currency currency = Currency.getInstance(currencyCode);
                return currency.getSymbol(currencyLocaleMap.get(currency));
            } else
                return "-";
        }

    }
}

