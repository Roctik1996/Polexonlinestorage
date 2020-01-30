package com.polexexpress.polexonlinestorage.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.polexexpress.polexonlinestorage.model.Group;
import com.polexexpress.polexonlinestorage.other.RxUtils;
import com.polexexpress.polexonlinestorage.provider.ProviderModule;
import com.polexexpress.polexonlinestorage.view.DeliveryDetailView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class DeliveryDetailPresenter extends MvpPresenter<DeliveryDetailView> {
    private CompositeDisposable compositeDisposable;

    public DeliveryDetailPresenter() {
        compositeDisposable = new CompositeDisposable();
    }


    public void refreshToken(String grant_type, String refresh_token) {
        addBackgroundDisposable(
                ProviderModule.getProvider().refreshToken(grant_type, refresh_token)
                        .compose(RxUtils.ioToMainTransformerSingle())
                        .subscribe(success -> getViewState().refreshToken(success), exception -> getViewState().showError("Произошла ошибка")));
    }

    public void getGroupDetailInvoice(String token, Group group) {
        getViewState().showProgress(true);
        addBackgroundDisposable(
                ProviderModule.getProvider().getGroupDetailInvoice(token, group)
                        .compose(RxUtils.ioToMainTransformerSingle())
                        .subscribe(success -> {
                                    getViewState().getGroupInvoice(success);
                                    getViewState().showProgress(false);
                                },
                                exception -> getViewState().showError("Ошибка")));
    }
    public void getGroupDetail(String token, String id) {
        getViewState().showProgress(true);
        addBackgroundDisposable(
                ProviderModule.getProvider().getGroupDetail(token, id)
                        .compose(RxUtils.ioToMainTransformerSingle())
                        .subscribe(success -> {
                                    getViewState().getGroupDetail(success);
                                    getViewState().showProgress(false);
                                },
                                exception -> getViewState().showError("Ошибка")));
    }

    public void finishShipment(String token, String groupId) {
        addBackgroundDisposable(
                ProviderModule.getProvider().finishShipment(token, groupId)
                        .compose(RxUtils.ioToMainTransformerSingle())
                        .subscribe(success -> getViewState().finishShipment(success),
                                exception -> getViewState().showError("Ошибка")));
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
