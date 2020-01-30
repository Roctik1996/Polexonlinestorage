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

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.polexexpress.polexonlinestorage.R;
import com.polexexpress.polexonlinestorage.model.ContentSearchInvoice;
import com.polexexpress.polexonlinestorage.model.Good;
import com.polexexpress.polexonlinestorage.model.Login;
import com.polexexpress.polexonlinestorage.other.Const;
import com.polexexpress.polexonlinestorage.presenter.DetailInvoicePresenter;
import com.polexexpress.polexonlinestorage.ui.adapter.InvoiceDetailAdapter;
import com.polexexpress.polexonlinestorage.view.DetailInvoiceView;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

public class InvoiceDetailActivity extends MvpAppCompatActivity implements DetailInvoiceView {

    private RecyclerView recyclerView;
    private ArrayList<Good> good;
    private String groupId;
    private TextView title, txtNum, txtFrom, txtTo, txtPhoneTo,
            txtFromCountry, txtToCountry, txtHAWB, txtCountPlace,
            txtTotalPrice, txtTotalWeight, txtDate;

    @InjectPresenter
    DetailInvoicePresenter detailInvoicePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice_detail);
        good = new ArrayList<>();
        Toolbar toolbar = findViewById(R.id.toolbar_invoice_detail);
        recyclerView = findViewById(R.id.recycler_detail_goods);
        title = findViewById(R.id.title_invoice_toolbar);
        ImageView check = findViewById(R.id.icon_check_detail);
        TextView txtCheck = findViewById(R.id.txt_detail_check);
        txtFrom = findViewById(R.id.txt_inv_from);
        txtTo = findViewById(R.id.txt_inv_to);
        txtPhoneTo = findViewById(R.id.txt_inv_phone);
        txtNum = findViewById(R.id.txt_num);
        txtFromCountry = findViewById(R.id.txt_inv_country_from);
        txtToCountry = findViewById(R.id.txt_inv_country_to);
        txtHAWB = findViewById(R.id.txt_inv_title);
        txtCountPlace = findViewById(R.id.txt_detail_count);
        txtTotalPrice = findViewById(R.id.txt_detail_cost);
        txtTotalWeight = findViewById(R.id.txt_detail_weight);
        txtDate = findViewById(R.id.txt_detail_date);


        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(view -> onBackPressed());


        detailInvoicePresenter.refreshToken(Const.grant_type_refresh, Const.refresh_token);
        String token = Const.token_type + " " + Const.token;
        String trackNo = getIntent().getStringExtra("track_no");
        detailInvoicePresenter.getInfo(token, trackNo);

        check.setOnClickListener(view -> {
            Intent scan = new Intent(this, CheckInvoiceActivity.class);
            scan.putExtra("check",true);
            scan.putExtra("trackNo",trackNo);
            startActivity(scan);
        });

        txtCheck.setOnClickListener(view -> {
            Intent scan = new Intent(this, CheckInvoiceActivity.class);
            scan.putExtra("check",true);
            scan.putExtra("trackNo",trackNo);
            startActivity(scan);
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getInfo(ContentSearchInvoice contentSearchInvoice) {
        groupId = String.valueOf(contentSearchInvoice.getSearchInvoice().getId());
        title.setText(contentSearchInvoice.getSearchInvoice().getTrackNo());
        txtNum.setText(contentSearchInvoice.getSearchInvoice().getTrackNo());
        txtFrom.setText(contentSearchInvoice.getSearchInvoice().getSenderName());
        txtTo.setText(contentSearchInvoice.getSearchInvoice().getReceiverName());
        txtPhoneTo.setText(contentSearchInvoice.getSearchInvoice().getReceiverPhone());
        txtFromCountry.setText(contentSearchInvoice.getSearchInvoice().getSenderCountry());
        txtToCountry.setText(contentSearchInvoice.getSearchInvoice().getReceiverCountry());
        txtHAWB.setText("HAWB: " + contentSearchInvoice.getSearchInvoice().getAwbNo());
        txtCountPlace.setText("Мест - " + contentSearchInvoice.getSearchInvoice().getPlaceNumber());
        txtTotalWeight.setText("Общий вес - " + contentSearchInvoice.getSearchInvoice().getTotalWeight());
        txtTotalPrice.setText("Сумма - " + Utils.getCurrencySymbol(contentSearchInvoice.getSearchInvoice().getCurrency()) + " " + contentSearchInvoice.getSearchInvoice().getTotalPrice());
        txtDate.setText(contentSearchInvoice.getSearchInvoice().getDate().replace("-", "."));

        good.addAll(contentSearchInvoice.getSearchInvoice().getGoods());

        InvoiceDetailAdapter adapter = new InvoiceDetailAdapter(this, good, Utils.getCurrencySymbol(contentSearchInvoice.getSearchInvoice().getCurrency()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showError(String error) {

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

    static class Utils {
        static SortedMap<Currency, Locale> currencyLocaleMap;

        static {
            currencyLocaleMap = new TreeMap<>((c1, c2) -> c1.getCurrencyCode().compareTo(c2.getCurrencyCode()));
            for (Locale locale : Locale.getAvailableLocales()) {
                try {
                    Currency currency = Currency.getInstance(locale);
                    currencyLocaleMap.put(currency, locale);
                } catch (Exception e) {
                    Log.d("InvoiceDetailActivity", Objects.requireNonNull(e.getMessage()));
                }
            }
        }

        static String getCurrencySymbol(String currencyCode) {
            if (currencyCode != null) {
                Currency currency = Currency.getInstance(currencyCode);
                return currency.getSymbol(currencyLocaleMap.get(currency));
            } else
                return "-";
        }

    }
}
