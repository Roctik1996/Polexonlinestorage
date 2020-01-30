package com.polexexpress.polexonlinestorage.view;

import com.arellomobile.mvp.MvpView;
import com.polexexpress.polexonlinestorage.model.GroupResult;
import com.polexexpress.polexonlinestorage.model.Login;

public interface DeliveryView extends MvpView {
    void getGroup(GroupResult groupResult);
    void showError(String error);
    void refreshToken(Login login);
    void showProgress(boolean isLoading);
}
