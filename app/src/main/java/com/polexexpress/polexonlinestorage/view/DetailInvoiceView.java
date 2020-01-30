package com.polexexpress.polexonlinestorage.view;

import com.arellomobile.mvp.MvpView;
import com.polexexpress.polexonlinestorage.model.ContentSearchInvoice;
import com.polexexpress.polexonlinestorage.model.Login;

public interface DetailInvoiceView extends MvpView {

    void getInfo(ContentSearchInvoice contentSearchInvoice);
    void showError(String error);
    void refreshToken(Login login);
    void showProgress(boolean isLoading);
}
