package com.polexexpress.polexonlinestorage.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.polexexpress.polexonlinestorage.R;
import com.polexexpress.polexonlinestorage.model.Login;
import com.polexexpress.polexonlinestorage.model.StatusCheckInvoice;
import com.polexexpress.polexonlinestorage.other.Const;
import com.polexexpress.polexonlinestorage.presenter.ShipmentPresenter;
import com.polexexpress.polexonlinestorage.view.ShipmentView;

import java.util.Objects;

public class ShipmentActivity extends MvpAppCompatActivity implements ShipmentView {

    private CodeScanner mCodeScanner;
    private TextView txtStatus, txtNext, scanNum;
    private CodeScannerView scannerView;

    @InjectPresenter
    ShipmentPresenter shipmentPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipment);
        txtStatus = findViewById(R.id.txt_status);
        txtNext = findViewById(R.id.txt_next);
        scanNum = findViewById(R.id.scan_num);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(view -> onBackPressed());


        scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(result -> runOnUiThread(() -> {
            shipmentPresenter.refreshToken(Const.grant_type_refresh, Const.refresh_token);
            String token = Const.token_type + " " + Const.token;
            shipmentPresenter.checkShipment(token, getIntent().getStringExtra("groupId"), result.getText());
            scanNum.setText(result.getText());
        }));
        scannerView.setOnClickListener(view -> {
            txtNext.setVisibility(View.INVISIBLE);
            txtStatus.setVisibility(View.INVISIBLE);
            mCodeScanner.startPreview();
            scannerView.setFrameColor(getColor(R.color.colorBase));
        });

        txtNext.setOnClickListener(view -> {
            txtNext.setVisibility(View.INVISIBLE);
            txtStatus.setVisibility(View.INVISIBLE);
            mCodeScanner.startPreview();
            scannerView.setFrameColor(getColor(R.color.colorBase));
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    @Override
    public void showSuccess(StatusCheckInvoice status) {
        if (status.getError().getStatus() == 200) {
            txtNext.setVisibility(View.VISIBLE);
            txtStatus.setTextColor(getColor(R.color.colorDone));
            txtStatus.setVisibility(View.VISIBLE);
            txtStatus.setText("Успешно найдена в партии");
            scannerView.setFrameColor(getColor(R.color.colorDone));
        }
    }

    @Override
    public void showError(String error) {
        txtStatus.setTextColor(getColor(R.color.colorError));
        txtStatus.setVisibility(View.VISIBLE);
        txtStatus.setText("Посылка не пренадлежит этой партии!");
        scannerView.setFrameColor(getColor(R.color.colorError));
    }

    @Override
    public void refreshToken(Login login) {
        Const.refresh_token = login.getRefreshToken();
        Const.token_type = login.getTokenType();
        Const.token = login.getAccessToken();
    }
}
