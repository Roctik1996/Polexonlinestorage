package com.polexexpress.polexonlinestorage.view;

import com.arellomobile.mvp.MvpView;
import com.polexexpress.polexonlinestorage.model.ContentDetail;
import com.polexexpress.polexonlinestorage.model.DetailGroupInvoice;
import com.polexexpress.polexonlinestorage.model.Login;
import com.polexexpress.polexonlinestorage.model.StatusCheckInvoice;

public interface DeliveryDetailView extends MvpView {

    void getGroupInvoice(DetailGroupInvoice detailGroupInvoice);

    void getGroupDetail(ContentDetail content);

    void finishShipment(StatusCheckInvoice status);

    void showError(String error);

    void refreshToken(Login login);

    void showProgress(boolean isLoading);
}
