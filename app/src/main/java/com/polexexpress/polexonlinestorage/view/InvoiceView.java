package com.polexexpress.polexonlinestorage.view;

import com.arellomobile.mvp.MvpView;
import com.polexexpress.polexonlinestorage.model.DetailGroupInvoice;
import com.polexexpress.polexonlinestorage.model.Login;

public interface InvoiceView extends MvpView {
    void getInvoice(DetailGroupInvoice groupResult);

    void showError(String error);

    void refreshToken(Login login);

    void showProgress(boolean isLoading);
}
