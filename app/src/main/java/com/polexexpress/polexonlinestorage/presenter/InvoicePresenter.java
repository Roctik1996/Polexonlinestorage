package com.polexexpress.polexonlinestorage.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.polexexpress.polexonlinestorage.model.Group;
import com.polexexpress.polexonlinestorage.other.RxUtils;
import com.polexexpress.polexonlinestorage.provider.ProviderModule;
import com.polexexpress.polexonlinestorage.view.InvoiceView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class InvoicePresenter extends MvpPresenter<InvoiceView> {
    private CompositeDisposable compositeDisposable;

    public InvoicePresenter() {
        compositeDisposable = new CompositeDisposable();
    }


    public void refreshToken(String grant_type, String refresh_token) {
        addBackgroundDisposable(
                ProviderModule.getProvider().refreshToken(grant_type, refresh_token)
                        .compose(RxUtils.ioToMainTransformerSingle())
                        .subscribe(success -> getViewState().refreshToken(success), exception -> getViewState().showError("Произошла ошибка")));
    }

    public void getInvoice(String token, Group group) {
        getViewState().showProgress(true);
        addBackgroundDisposable(
                ProviderModule.getProvider().getGroupDetailInvoice(token, group)
                        .compose(RxUtils.ioToMainTransformerSingle())
                        .subscribe(success -> {
                                    getViewState().getInvoice(success);
                                    getViewState().showProgress(false);
                                },
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
