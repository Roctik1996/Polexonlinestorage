package com.polexexpress.polexonlinestorage.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.polexexpress.polexonlinestorage.model.Group;
import com.polexexpress.polexonlinestorage.other.RxUtils;
import com.polexexpress.polexonlinestorage.provider.ProviderModule;
import com.polexexpress.polexonlinestorage.view.DeliveryView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class DeliveryPresenter extends MvpPresenter<DeliveryView> {
    private CompositeDisposable compositeDisposable;

    public DeliveryPresenter() {
        compositeDisposable = new CompositeDisposable();
    }


    public void refreshToken(String grant_type, String refresh_token){
        addBackgroundDisposable(
                ProviderModule.getProvider().refreshToken(grant_type, refresh_token)
                        .compose(RxUtils.ioToMainTransformerSingle())
                        .subscribe(success -> getViewState().refreshToken(success), exception -> getViewState().showError("Произошла ошибка")));
    }
    public void getGroup(String token, Group group) {
        getViewState().showProgress(true);
        addBackgroundDisposable(
                ProviderModule.getProvider().getGroup(token, group)
                        .compose(RxUtils.ioToMainTransformerSingle())
                        .subscribe(success -> {
                                    getViewState().getGroup(success);
                                    getViewState().showProgress(false);
                                },
                                exception-> getViewState().showError("Ошибка")));
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
