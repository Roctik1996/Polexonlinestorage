package com.polexexpress.polexonlinestorage.view;

import com.arellomobile.mvp.MvpView;
import com.polexexpress.polexonlinestorage.model.ContentStoreInfo;
import com.polexexpress.polexonlinestorage.model.Login;

public interface MainView extends MvpView {

    void showProgress(boolean isLoading);
    void showError(String error);
    void getInfo(ContentStoreInfo contentStoreInfo);
    void refreshToken(Login login);
}
