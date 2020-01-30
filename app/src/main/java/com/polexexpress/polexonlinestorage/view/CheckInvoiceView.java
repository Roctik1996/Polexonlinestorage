package com.polexexpress.polexonlinestorage.view;

import com.arellomobile.mvp.MvpView;
import com.polexexpress.polexonlinestorage.model.Login;
import com.polexexpress.polexonlinestorage.model.StatusCheckInvoice;

public interface CheckInvoiceView extends MvpView {
    void showSuccess(StatusCheckInvoice status);
    void showError(String error);
    void refreshToken(Login login);
}
