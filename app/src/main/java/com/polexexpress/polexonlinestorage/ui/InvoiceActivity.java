package com.polexexpress.polexonlinestorage.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.google.android.material.navigation.NavigationView;
import com.polexexpress.polexonlinestorage.R;
import com.polexexpress.polexonlinestorage.model.DetailGroupInvoice;
import com.polexexpress.polexonlinestorage.model.Filter;
import com.polexexpress.polexonlinestorage.model.Group;
import com.polexexpress.polexonlinestorage.model.Login;
import com.polexexpress.polexonlinestorage.model.PagingInfo;
import com.polexexpress.polexonlinestorage.model.SearchInvoice;
import com.polexexpress.polexonlinestorage.model.SortInfo;
import com.polexexpress.polexonlinestorage.other.Const;
import com.polexexpress.polexonlinestorage.presenter.InvoicePresenter;
import com.polexexpress.polexonlinestorage.ui.adapter.InvoiceAdapter;
import com.polexexpress.polexonlinestorage.view.InvoiceView;

import java.util.ArrayList;
import java.util.Objects;

public class InvoiceActivity extends MvpAppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, InvoiceView {

    private RecyclerView recyclerView;
    private ArrayList<SearchInvoice> data;
    private static final String APP_PREFERENCES = "setting";
    private static final String APP_PREFERENCES_USERNAME = "username";
    private static final String APP_PREFERENCES_PASS = "pass";
    private SharedPreferences mSettings;
    boolean check = true;
    InvoiceAdapter adapter;
    int i = 0, current, end;
    String token;

    @InjectPresenter
    InvoicePresenter invoicePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = new ArrayList<>();
        setContentView(R.layout.activity_invoice);
        Toolbar toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler_invoice);

        mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        invoicePresenter.refreshToken(Const.grant_type_refresh, Const.refresh_token);
        token = Const.token_type + " " + Const.token;

        Group group = new Group();
        group.setFilter(new Filter());

        PagingInfo pagingInfo = new PagingInfo();
        pagingInfo.setPageNum(i);
        pagingInfo.setPageSize(10);
        group.setPagingInfo(pagingInfo);

        ArrayList<SortInfo> sortInfos = new ArrayList<>();
        SortInfo sortInfo = new SortInfo();
        sortInfo.setFieldName("id");
        sortInfo.setSortDirection("desc");
        sortInfos.add(sortInfo);
        group.setSortInfos(sortInfos);

        invoicePresenter.getInvoice(token, group);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
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
            finish();
        } else if (id == R.id.nav_all) {
            Intent all = new Intent(this, InvoiceActivity.class);
            startActivity(all);
            finish();
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
    public void getInvoice(DetailGroupInvoice groupResult) {
        data.addAll(groupResult.getContentDetailGroupInvoice().getData());
        current = groupResult.getContentDetailGroupInvoice().getPageData().getPageNum();
        end = groupResult.getContentDetailGroupInvoice().getPageData().getTotalPages();
        if (check) {
            adapter = new InvoiceAdapter(data);
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(onItemClickListener);
            check = false;
        }
        try {
            adapter.notifyDataSetChanged();
        } catch (NullPointerException e) {
            e.getMessage();
        }
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == data.size() - 1) {
                    //bottom of list!

                    if (current < end) {
                        data.add(null);
                        adapter.notifyItemInserted(data.size() - 1);
                        data.remove(data.size() - 1);
                        int scrollPosition = data.size();
                        adapter.notifyItemRemoved(scrollPosition);
                        i++;
                        Group group = new Group();
                        group.setFilter(new Filter());

                        PagingInfo pagingInfo = new PagingInfo();
                        pagingInfo.setPageNum(i);
                        pagingInfo.setPageSize(10);
                        group.setPagingInfo(pagingInfo);

                        ArrayList<SortInfo> sortInfos = new ArrayList<>();
                        SortInfo sortInfo = new SortInfo();
                        sortInfo.setFieldName("id");
                        sortInfo.setSortDirection("desc");
                        sortInfos.add(sortInfo);
                        group.setSortInfos(sortInfos);
                        invoicePresenter.getInvoice(token, group);

                    }
                }


            }

        });


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
}
