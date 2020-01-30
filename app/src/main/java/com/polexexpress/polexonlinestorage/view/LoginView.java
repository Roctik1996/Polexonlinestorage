package com.polexexpress.polexonlinestorage.view;

import com.arellomobile.mvp.MvpView;
import com.polexexpress.polexonlinestorage.model.Login;

public interface LoginView extends MvpView {

    void showProgress(boolean isLoading);
    void showError(String error);
    void signIn(Login login);
}
