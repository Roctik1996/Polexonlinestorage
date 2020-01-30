package com.polexexpress.polexonlinestorage.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.navigation.NavigationView;
import com.polexexpress.polexonlinestorage.R;
import com.polexexpress.polexonlinestorage.model.ContentStoreInfo;
import com.polexexpress.polexonlinestorage.model.Login;
import com.polexexpress.polexonlinestorage.other.Const;
import com.polexexpress.polexonlinestorage.presenter.StoreInfoPresenter;
import com.polexexpress.polexonlinestorage.view.MainView;

import java.util.Objects;

public class MainActivity extends MvpAppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MainView {

    private static final String APP_PREFERENCES = "setting";
    private static final String APP_PREFERENCES_USERNAME = "username";
    private static final String APP_PREFERENCES_PASS = "pass";
    private SharedPreferences mSettings;

    private TextView myInvoiceCount, groupCount, totalInvoiceCount;


    @InjectPresenter
    StoreInfoPresenter storeInfoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        ImageView iconDelivery = findViewById(R.id.icon_delivery);
        TextView txtDelivery = findViewById(R.id.txt_delivery);

        ImageView iconCheck = findViewById(R.id.icon_check);
        TextView txtCheck = findViewById(R.id.txt_check);

        ImageView iconAll = findViewById(R.id.icon_all_btn);
        TextView txtAll = findViewById(R.id.txt_all);

        myInvoiceCount = findViewById(R.id.txtMy);
        groupCount = findViewById(R.id.txtAll);
        totalInvoiceCount = findViewById(R.id.txtStorage);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);


        storeInfoPresenter.refreshToken(Const.grant_type_refresh,Const.refresh_token);
        String token = Const.token_type + " " + Const.token;
        storeInfoPresenter.getStoreInfo(token);

        iconDelivery.setOnClickListener(v -> {
            Intent delivery = new Intent(this, DeliveryActivity.class);
            startActivity(delivery);
        });
        txtDelivery.setOnClickListener(v -> {
            Intent delivery = new Intent(this, DeliveryActivity.class);
            startActivity(delivery);
        });

        iconCheck.setOnClickListener(v -> {
            Intent delivery = new Intent(this, CheckActivity.class);
            startActivity(delivery);
            finish();
        });
        txtCheck.setOnClickListener(v -> {
            Intent delivery = new Intent(this, CheckActivity.class);
            startActivity(delivery);
        });

        iconAll.setOnClickListener(v -> {
            Intent invoice = new Intent(this, InvoiceActivity.class);
            startActivity(invoice);
        });
        txtAll.setOnClickListener(v -> {
            Intent invoice = new Intent(this, InvoiceActivity.class);
            startActivity(invoice);
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_add) {

        } else if (id == R.id.nav_check) {
            Intent check = new Intent(this, CheckActivity.class);
            startActivity(check);
            finish();

        } else if (id == R.id.nav_delivery) {
            Intent delivery = new Intent(this, DeliveryActivity.class);
            startActivity(delivery);
        } else if (id == R.id.nav_all) {
            Intent all = new Intent(this, InvoiceActivity.class);
            startActivity(all);
        } else if (id == R.id.nav_exit) {
            Intent exit = new Intent(this, LoginActivity.class);
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString(APP_PREFERENCES_USERNAME, "");
            editor.putString(APP_PREFERENCES_PASS, "");
            editor.apply();
            Const.token = "";
            startActivity(exit);
            finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showProgress(boolean isLoading) {

    }

    @Override
    public void showError(String error) {

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void getInfo(ContentStoreInfo contentStoreInfo) {
        myInvoiceCount.setText(String.valueOf(contentStoreInfo.getStoreInfo().getMyInvoiceCount()));
        groupCount.setText(contentStoreInfo.getStoreInfo().getGroupCount() + "/" + contentStoreInfo.getStoreInfo().getGroupsInvoiceCount());
        totalInvoiceCount.setText(String.valueOf(contentStoreInfo.getStoreInfo().getTotalInvoiceCount()));
    }

    @Override
    public void refreshToken(Login login) {
        Const.refresh_token = login.getRefreshToken();
        Const.token_type = login.getTokenType();
        Const.token = login.getAccessToken();
    }
}
