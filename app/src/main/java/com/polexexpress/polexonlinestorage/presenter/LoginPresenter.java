package com.polexexpress.polexonlinestorage.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.polexexpress.polexonlinestorage.other.RxUtils;
import com.polexexpress.polexonlinestorage.provider.ProviderModule;
import com.polexexpress.polexonlinestorage.view.LoginView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    private CompositeDisposable compositeDisposable;

    public LoginPresenter() {
        compositeDisposable = new CompositeDisposable();
    }


    public void signIn(String grant_type, String username, String pass) {
        getViewState().showProgress(true);
        addBackgroundDisposable(
                ProviderModule.getProvider().login(grant_type, username, pass)
                        .compose(RxUtils.ioToMainTransformerSingle())
                        .subscribe(success -> {
                            getViewState().signIn(success);
                            getViewState().showProgress(false);
                        }, exception -> {
                            getViewState().showProgress(false);
                            getViewState().showError("Неверное имя пользователя или пароль, повторите попытку");
                        }));
    }

    private void addBackgroundDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    protected void finalize() throws Throwable {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
        super.finalize();
    }
}
