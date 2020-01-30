package com.polexexpress.polexonlinestorage.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.polexexpress.polexonlinestorage.R;
import com.polexexpress.polexonlinestorage.model.Error;
import com.polexexpress.polexonlinestorage.model.Login;
import com.polexexpress.polexonlinestorage.model.StatusCheckInvoice;
import com.polexexpress.polexonlinestorage.other.Const;
import com.polexexpress.polexonlinestorage.presenter.CheckInvoicePresenter;
import com.polexexpress.polexonlinestorage.view.CheckInvoiceView;

import java.util.Objects;

public class CheckInvoiceActivity extends MvpAppCompatActivity implements CheckInvoiceView {

    private CodeScanner mCodeScanner;
    private TextView txtError, scanNum;
    private ImageView icon;
    private CodeScannerView scannerView;

    @InjectPresenter
    CheckInvoicePresenter checkInvoicePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_invoice);
        icon = findViewById(R.id.icon_error);
        txtError = findViewById(R.id.txt_error);
        scanNum = findViewById(R.id.scan_num);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(view -> onBackPressed());


        scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(result -> runOnUiThread(() -> {
            if (getIntent().getBooleanExtra("check", false))
            {
                if (getIntent().getStringExtra("trackNo").equals(result.getText())) {
                    StatusCheckInvoice status = new StatusCheckInvoice();
                    Error error = new Error();
                    error.setStatus(200);
                    status.setError(error);
                    showSuccess(status);
                    scanNum.setText(result.getText());
                }
                else {
                    showError("error");
                    scanNum.setText(result.getText());
                }
            }
            else {
                checkInvoicePresenter.refreshToken(Const.grant_type_refresh, Const.refresh_token);
                String token = Const.token_type + " " + Const.token;
                checkInvoicePresenter.checkInvoice(token, getIntent().getStringExtra("groupId"), result.getText());
                scanNum.setText(result.getText());
            }
        }));
        scannerView.setOnClickListener(view -> mCodeScanner.startPreview());

        icon.setOnClickListener(view -> onBackPressed());
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
            txtError.setVisibility(View.VISIBLE);
            txtError.setText("Код посылки совпадает");
            txtError.setTextColor(getColor(R.color.colorDone));
            icon.setVisibility(View.VISIBLE);
            icon.setImageResource(R.drawable.ic_done);
            scannerView.setFrameColor(getColor(R.color.colorDone));
        }
    }

    @Override
    public void showError(String error) {
        txtError.setVisibility(View.VISIBLE);
        txtError.setText("Не верный код посылки");
        txtError.setTextColor(getColor(R.color.colorError));
        icon.setVisibility(View.VISIBLE);
        icon.setImageResource(R.drawable.ic_error);
        scannerView.setFrameColor(getColor(R.color.colorError));
    }

    @Override
    public void refreshToken(Login login) {
        Const.refresh_token = login.getRefreshToken();
        Const.token_type = login.getTokenType();
        Const.token = login.getAccessToken();
    }
}
