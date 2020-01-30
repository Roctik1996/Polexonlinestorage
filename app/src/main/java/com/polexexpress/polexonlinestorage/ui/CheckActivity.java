package com.polexexpress.polexonlinestorage.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.polexexpress.polexonlinestorage.R;
import com.polexexpress.polexonlinestorage.model.ContentSearchInvoice;
import com.polexexpress.polexonlinestorage.model.Login;
import com.polexexpress.polexonlinestorage.other.Const;
import com.polexexpress.polexonlinestorage.presenter.DetailInvoicePresenter;
import com.polexexpress.polexonlinestorage.view.DetailInvoiceView;

import java.util.Objects;


public class CheckActivity extends MvpAppCompatActivity implements DetailInvoiceView {

    private CodeScanner mCodeScanner;
    private TextView txtOK;
    private TextView txtError;
    private TextView scanNum;
    private CodeScannerView scannerView;


    @InjectPresenter
    DetailInvoicePresenter detailInvoicePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        TextView toolbarTitle = findViewById(R.id.title_toolbar_scan);
        txtOK = findViewById(R.id.scanner_ok);
        txtError = findViewById(R.id.txt_error);
        scanNum = findViewById(R.id.scan_num);

        toolbarTitle.setText("Проверить посылку");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(view -> {
            Intent back = new Intent(this, MainActivity.class);
            startActivity(back);
            finish();
        });
        Intent intent = getIntent();
        String inVisible = intent.getStringExtra("from");
        if (inVisible != null) {
            if (inVisible.equals("true")) {
                txtOK.setVisibility(View.INVISIBLE);
                toolbarTitle.setText(intent.getStringExtra("title"));
            }
        }
        scannerView = findViewById(R.id.scanner_view);
        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(result -> runOnUiThread(() -> {
            detailInvoicePresenter.refreshToken(Const.grant_type_refresh, Const.refresh_token);
            String token = Const.token_type + " " + Const.token;
            detailInvoicePresenter.getInfo(token, result.getText());
        }));
        scannerView.setOnClickListener(view -> mCodeScanner.startPreview());

        txtOK.setOnClickListener(view -> {
            Intent back = new Intent(this, MainActivity.class);
            startActivity(back);
            finish();
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
    public void getInfo(ContentSearchInvoice contentSearchInvoice) {
        Intent detail = new Intent(this, InvoiceDetailActivity.class);
        detail.putExtra("track_no", contentSearchInvoice.getSearchInvoice().getTrackNo());
        scanNum.setText(contentSearchInvoice.getSearchInvoice().getTrackNo());
        startActivity(detail);
        finish();
    }

    @Override
    public void showError(String error) {
        txtError.setVisibility(View.VISIBLE);
        txtError.setText(error);
        txtOK.setVisibility(View.VISIBLE);
        scannerView.setFrameColor(getColor(R.color.colorError));

    }

    @Override
    public void refreshToken(Login login) {
        Const.refresh_token = login.getRefreshToken();
        Const.token_type = login.getTokenType();
        Const.token = login.getAccessToken();

    }

    @Override
    public void showProgress(boolean isLoading) {

    }
}
