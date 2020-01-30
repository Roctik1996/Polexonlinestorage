package com.polexexpress.polexonlinestorage.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.polexexpress.polexonlinestorage.other.RxUtils;
import com.polexexpress.polexonlinestorage.provider.ProviderModule;
import com.polexexpress.polexonlinestorage.view.MainView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class StoreInfoPresenter extends MvpPresenter<MainView> {

    private CompositeDisposable compositeDisposable;

    public StoreInfoPresenter() {
        compositeDisposable = new CompositeDisposable();
    }


    public void refreshToken(String grant_type, String refresh_token) {
        addBackgroundDisposable(
                ProviderModule.getProvider().refreshToken(grant_type, refresh_token)
                        .compose(RxUtils.ioToMainTransformerSingle())
                        .subscribe(success -> getViewState().refreshToken(success), exception -> getViewState().showError("Произошла ошибка")));
    }

    public void getStoreInfo(String token) {
        getViewState().showProgress(true);
        addBackgroundDisposable(
                ProviderModule.getProvider().getStoreInfo(token)
                        .compose(RxUtils.ioToMainTransformerSingle())
                        .subscribe(success -> {
                            getViewState().getInfo(success);
                            getViewState().showProgress(false);
                        }, exception -> {
                            getViewState().showProgress(false);
                            getViewState().showError("Произошла ошибка");
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
