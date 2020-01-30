package com.polexexpress.polexonlinestorage.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.polexexpress.polexonlinestorage.other.RxUtils;
import com.polexexpress.polexonlinestorage.provider.ProviderModule;
import com.polexexpress.polexonlinestorage.view.DetailInvoiceView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class DetailInvoicePresenter extends MvpPresenter<DetailInvoiceView> {

    private CompositeDisposable compositeDisposable;

    public DetailInvoicePresenter() {
        compositeDisposable = new CompositeDisposable();
    }


    public void refreshToken(String grant_type, String refresh_token){
        addBackgroundDisposable(
                ProviderModule.getProvider().refreshToken(grant_type, refresh_token)
                        .compose(RxUtils.ioToMainTransformerSingle())
                        .subscribe(success -> getViewState().refreshToken(success), exception -> getViewState().showError("Произошла ошибка")));
    }
    public void getInfo(String token,String track_no) {
        getViewState().showProgress(true);
        addBackgroundDisposable(
                ProviderModule.getProvider().checkInvoice(token, track_no)
                        .compose(RxUtils.ioToMainTransformerSingle())
                        .subscribe(success -> {
                            getViewState().getInfo(success);
                            getViewState().showProgress(false);
                                },
                                exception-> getViewState().showError("Посылка не найдена")));
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
